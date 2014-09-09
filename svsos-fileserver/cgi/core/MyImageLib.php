<?php

/*
 * mode 1 : 强制裁剪，生成图片严格按照需要，不足放大，超过居中裁剪，图片始终铺满
 * mode 2 : 只缩放，不裁剪，保留全部图片信息，生成图片大小为最终缩放后的图片有效信息的实际大小，不产生补白
 *
 * 调用方法：
 *
 * $img =new MyImageCrop('old.jpg','afterCrop.jpg');
 * $img->Crop(120,80,1);
 *
 */
class MyImageCrop
{
    var $sImage;
    var $dImage;
    var $src_file;
    var $dst_file;
    var $src_width;
    var $src_height;
    var $src_ext;
    var $src_type;

    function __construct($src_file, $dst_file = '')
    {
        $this->src_file = $src_file;
        $this->dst_file = $dst_file;
        $this->LoadImage();
    }

    function LoadImage()
    {
        list($this->src_width, $this->src_height, $this->src_type) = getimagesize($this->src_file);
        switch ($this->src_type)
        {
            case IMAGETYPE_JPEG :
                $this->sImage = imagecreatefromjpeg($this->src_file);
                $this->ext    = 'jpg';
                break;
            case IMAGETYPE_PNG :
                $this->sImage = imagecreatefrompng($this->src_file);
                $this->ext    = 'png';
                break;
            case IMAGETYPE_GIF :
                $this->sImage = imagecreatefromgif($this->src_file);
                $this->ext    = 'gif';
                break;
            default:
                exit();
        }
    }

    function SaveImage($fileName = '')
    {
        $this->dst_file = $fileName ? $fileName : $this->dst_file;
        switch ($this->src_type)
        {
            case IMAGETYPE_JPEG :
                imagejpeg($this->dImage, $this->dst_file, 100);
                break;
            case IMAGETYPE_PNG :
                imagepng($this->dImage, $this->dst_file);
                break;
            case IMAGETYPE_GIF :
                imagegif($this->dImage, $this->dst_file);
                break;
            default:
                break;
        }
    }

    function destory()
    {
        imagedestroy($this->sImage);
        imagedestroy($this->dImage);
    }

    function Crop($dst_width, $dst_height, $mode = 1, $dst_file = '')
    {
        if ($dst_file)
            $this->dst_file = $dst_file;
        $this->dImage   = imagecreatetruecolor($dst_width, $dst_height);

        $bg = imagecolorallocatealpha($this->dImage, 255, 255, 255, 127);
        imagefill($this->dImage, 0, 0, $bg);
        imagecolortransparent($this->dImage, $bg);

        $ratio_w = 1.0 * $dst_width / $this->src_width;
        $ratio_h = 1.0 * $dst_height / $this->src_height;
        $ratio   = 1.0;
        switch ($mode)
        {
            case 1:
                if (($ratio_w < 1 && $ratio_h < 1) || ($ratio_w > 1 && $ratio_h > 1))
                {
                    $ratio   = $ratio_w < $ratio_h ? $ratio_h : $ratio_w;
                    $tmp_w   = (int) ($dst_width / $ratio);
                    $tmp_h   = (int) ($dst_height / $ratio);
                    $tmp_img = imagecreatetruecolor($tmp_w, $tmp_h);
                    $src_x   = (int) (($this->src_width - $tmp_w) / 2);
                    $src_y   = (int) (($this->src_height - $tmp_h) / 2);
                    imagecopy($tmp_img, $this->sImage, 0, 0, $src_x, $src_y, $tmp_w, $tmp_h);
                    imagecopyresampled($this->dImage, $tmp_img, 0, 0, 0, 0, $dst_width, $dst_height, $tmp_w, $tmp_h);
                    imagedestroy($tmp_img);
                }
                else
                {
                    $ratio   = $ratio_w < $ratio_h ? $ratio_h : $ratio_w;
                    $tmp_w   = (int) ($this->src_width * $ratio);
                    $tmp_h   = (int) ($this->src_height * $ratio);
                    $tmp_img = imagecreatetruecolor($tmp_w, $tmp_h);
                    imagecopyresampled($tmp_img, $this->sImage, 0, 0, 0, 0, $tmp_w, $tmp_h, $this->src_width, $this->src_height);
                    $src_x   = (int) ($tmp_w - $dst_width) / 2;
                    $src_y   = (int) ($tmp_h - $dst_height) / 2;
                    imagecopy($this->dImage, $tmp_img, 0, 0, $src_x, $src_y, $dst_width, $dst_height);
                    imagedestroy($tmp_img);
                }
                break;
            case 2:
                if ($ratio_w > 1 && $ratio_h > 1)
                {
                    $this->dImage = imagecreatetruecolor($this->src_width, $this->src_height);
                    imagecopy($this->dImage, $this->sImage, 0, 0, 0, 0, $this->src_width, $this->src_height);
                }
                else
                {
                    $ratio        = $ratio_w > $ratio_h ? $ratio_h : $ratio_w;
                    $tmp_w        = (int) ($this->src_width * $ratio);
                    $tmp_h        = (int) ($this->src_height * $ratio);
                    $this->dImage = imagecreatetruecolor($tmp_w, $tmp_h);
                    imagecopyresampled($this->dImage, $this->sImage, 0, 0, 0, 0, $tmp_w, $tmp_h, $this->src_width, $this->src_height);
                }
                break;
        }

        $this->SaveImage();
        $this->destory();

        $imgInfo = array('width'=>$this->src_width, 'height'=>$this->src_height);
        return $imgInfo;        
    }
}
