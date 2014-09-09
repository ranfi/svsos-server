<?php

abstract class displayer {

    private $mod_num = 1000;

    protected function __construct() {
        
    }

    protected function path_as_array($path) {
        if ($path) {
            $path_array = explode('/', $path);
            foreach ($path_array as $value) {
                if ($value) {
                    $array[] = $value;
                }
            }
            return $array;
        }
    }

    /**
     * 生成hash值
     *
     * @param bool $more
     * @param integer $len
     * @return
     */
    protected function uuid($more = true, $len = 5) {
        if ($more) {
            return uniqid(md5(mt_rand()), true);
        } else {
            return uniqid(self::create_guid_section($len), false);
        }
    }

    protected function create_guid_section($characters) {
        $return = '';
        for ($i = 0; $i < $characters; $i++) {
            $return .= dechex(mt_rand(0, 15));
        }
        return $return;
    }

    /*
      protected function mm_init($type)
      {
      global $config;
      if ( $type )
      {
      $memcache = new Memcache;
      foreach ( $config['mm'][$type] as $server )
      {
      $memcache->addServer($server['host'],$server['port']);
      }
      return $memcache;
      }
      }

     */

    protected function getFilePath($dir, $key, $url = FALSE, $fileext = '.jpg') {

        $firstFolder = substr($key, 0, 1);
        $secondFolder = substr($key, 1, 2);
        $lastFolder = substr($key, 3, 2);
        $path = $dir . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $key . $fileext;

        if ($url) {
            return IMG_DOMAIN . 'pic/' . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $key . $fileext;
        }

        if (file_exists($path)) {
            return $dir . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/';
        } else {
            return false;
        }
    }

    /**
     * 获取图片所在文件夹
     *
     * @return
     */
    protected function getHashFolder($prefixFolder = "") {
        $this->firstFolder = substr($this->hashKey, 0, 1);
        $this->secondFolder = substr($this->hashKey, 1, 2);
        $this->lastFolder = substr($this->hashKey, 3, 2);

        return $prefixFolder."/".$this->firstFolder . '/' . $this->secondFolder . '/' . $this->lastFolder . '/';
    }

    /**
     *
     * 根据用户ID取整和取余，拼装图片存放目录
     *
     */
    protected function getHashFolderByUid() {

        $firstFolder = (int) ($this->uid / $this->mod_num);
        $secondFolder = $this->uid % $this->mod_num;

        return $firstFolder . '/' . $secondFolder . '/';
    }

    /**
     * 创建用户一系列文件夹
     *
     * @return
     */
    protected function createImageFolder() {
        /* 创建用户图片文件夹 */
        mkdir($this->imgPath, 0755, true);

        return $this->imgPath;
    }

    protected function adjust_img($pic_path, $sizes, $new_path, $is_show = TRUE, $type = 2) {
        if (!class_exists('MyImageCrop'))
            include('MyImageLib.php');
        $size = explode('x', $sizes);
        $img = new MyImageCrop($pic_path, $new_path);
        $imginfo = $img->Crop($size[0], $size[1], $type);
        if ($is_show) {
            $this->cache_send($new_path, filemtime($new_path));
        } else {
            $imginfo['new_path'] = $new_path;
            return $imginfo;
        }
    }

    /*
     * @name 图片上传处理
     * @param filename String file名
     * @param watermark Bool 水印
     * @retrun Mixed
     */

    protected function upload_img($filename = 'upload', $watermark = FALSE) {
        $ret = $this->_do_upload($filename);
        if (isset($ret['key'])) {
            //加水印
            if ($watermark) {
                if (!class_exists('Image_lib'))
                    include('Image_lib.php');
            }
        }

        return $ret;
    }

    protected function _do_upload($filename) {
        if (!class_exists('uploadFile'))
            include('upload.php');

        if (is_dir($this->imgPath)) {
            $userUploadFolderPath = $this->imgPath;
        } else {
            $userUploadFolderPath = $this->createImageFolder();
        }

        $config['upload_path'] = $userUploadFolderPath;
        $config['allowed_types'] = 'gif|jpg|png|jpeg';
        $config['file_name'] = $this->hashKey;
        $uploadClass = new uploadFile($config);
        if (!$uploadClass->do_upload($filename)) {
            return array('error_code' => 9003, 'cid' => $filename, 'error_msg' => $uploadClass->display_errors());
        } else {
            $data = array('upload_data' => $uploadClass->data());
            if (empty($data['upload_data'])) {
                return array('error_code' => 9003, 'cid' => $filename, 'error_msg' => 'upload has an exception occured');
            }

            if ($this->thumbnail) {
                $thumbnail_size_arr = explode('x', $this->thumbnail);
                $desc_path = $data['upload_data']['file_path'] . $this->hashKey . "_" . $this->thumbnail . $data['upload_data']['file_ext'];
                $imginfo = $this->adjust_img($data['upload_data']['pic_path'], $this->thumbnail, $desc_path, FALSE);
                $data['upload_data']['file_name'] = $this->hashKey . "_" . $this->thumbnail . $data['upload_data']['file_ext'];
                $data['upload_data']['width'] = $thumbnail_size_arr[0];
                $data['upload_data']['height'] = $thumbnail_size_arr[1];
            }

            if ($this->create) {
                $file_path = $data['upload_data']['file_path'];
                $file_c_name = $this->hashKey . '_c.jpg';
                $file_z_name = $this->hashKey . '_z.jpg';

                $this->adjust_img($data['upload_data']['pic_path'], $this->create, $file_path . $file_c_name, FALSE, 1);
                $imginfo = $this->adjust_img($data['upload_data']['pic_path'], SQUARE, $file_path . $file_z_name, FALSE, 1);
            } else {
                $imginfo = $data['upload_data']['pic_path'];
            }

            if (empty($imginfo)) {
                return array('error_code' => 9004, 'cid' => $filename, 'error_msg' => 'picture process has an exception occured!');
            } else {
                $width = $data['upload_data']['width'];
                $height = $data['upload_data']['height'];
                $ret['pic_id'] = $this->hashKey;
                $ret['pic_path'] = IMG_DOMAIN . $this->hashFolder . $data['upload_data']['file_name'];
                $ret['width'] = (isset($width) && is_numeric($width)) ? $width : 0;
                $ret['height'] = (isset($height) && is_numeric($height)) ? $height : 0;
                return $ret;
            }
        }
    }

    protected function send($content) {
        header("Content-type: image/jpeg");
        echo $content;
        exit;
    }

    protected function cache_show($dir, $filename) {
        $firstFolder = substr($filename, 0, 1);
        $secondFolder = substr($filename, 1, 2);
        $lastFolder = substr($filename, 3, 2);
        $path = $dir . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $filename;
        if (file_exists($path)) {
            $this->cache_send($path);
        }
    }

    protected function cache_send($file) {
        $timestamp = filemtime($file);
        $gmt_mtime = gmdate('r', $timestamp);
        header('ETag: "' . md5($timestamp . $file) . '"');
        if (isset($_SERVER['HTTP_IF_MODIFIED_SINCE']) || isset($_SERVER['HTTP_IF_NONE_MATCH'])) {
            if ($_SERVER['HTTP_IF_MODIFIED_SINCE'] == $gmt_mtime || str_replace('"', '', stripslashes($_SERVER['HTTP_IF_NONE_MATCH'])) == md5($timestamp . $file)) {
                header('HTTP/1.1 304 Not Modified');
                exit();
            }
        }
        header('Last-Modified: ' . $gmt_mtime);
        header('Cache-Control: public');
        header("Content-type: image/jpeg");
        echo file_get_contents($file);
        exit;
    }

}
