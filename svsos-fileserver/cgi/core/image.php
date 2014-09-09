<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
include_once('cgi/config/constant.php');
include_once('cgi/core/uploadFile.php');
include_once('cgi/core/fileProcess.php');
include_once('cgi/util/fileHelper.php');
include_once('cgi/util/httpHelper.php');

class Image extends UploadFile implements FileProcess {

    private $paths;
    private $query;
    private $thumb = false;
    private $create = false;
    private $hashKey;
    private $hashFolder;
    private $imgPath;
    private $prefixFolder = "img";

    public function __construct($path, $query) {
        $this->paths = FileHelper::path_as_array($path);
        $this->query = $query;
        parent::__construct($props = array());
    }

    /*
     * @name 图片上传处理
     * @param filename String file名
     * @param watermark Bool 水印
     * @retrun Mixed
     */

    public function doUpload() {
        if (!empty($_FILES)) {
            //检查是否需要裁剪图片
            $thumbKey = array_pop($this->paths);
            $thumbArray = explode('_', $thumbKey);
            $this->thumb = false;
            if ($thumbArray[0] == 'thumb') {
                $dimen = $thumbArray[1];
                if (isset($dimen)) {
                    $this->thumb = $dimen;
                    if (!stristr($dimen, 'x')) {
                        HttpHelper::sendException(Constants::$IMAGE_SIZE_UNDEFINED);
                    }
                }
            }
            //图片上传处理
            $data = array();
            $flag = TRUE;
            foreach ($_FILES as $filename => $v) {
                $this->hashKey = FileHelper::uuid(false);
                $this->hashFolder = FileHelper::getHashFolder($this->prefixFolder, $this->hashKey);
                $this->imgPath = FILE_FOLDER . $this->hashFolder;
                $data[] = $this->_upload($filename);
            }
            echo json_encode($data);
        } else {
            HttpHelper::sendException(Constants::$UPLOAD_FILE_NOT_EMPTY);
        }
    }

    private function _upload($filename) {
        if (is_dir($this->imgPath)) {
            $userUploadFolderPath = $this->imgPath;
        } else {
            $userUploadFolderPath = FileHelper::createServerFolder($this->imgPath);
        }

        $config['upload_path'] = $userUploadFolderPath;
        $config['allowed_types'] = 'gif|jpg|png|jpeg';
        $config['file_name'] = $this->hashKey;
        $this->setConfig($config);
        if (!$this->_doUpload($filename)) {
            $exception = Constants::$UPLOAD_FILE_ERROR;
            $exception["filename"] = $filename;
            $exception["error_msg"] = $this->display_errors();
            return $exception;
        } else {
            $data = array('upload_data' => $this->getData());
            if (empty($data['upload_data'])) {
                return Constants::$UNKNOW_ERROR;
            }

            if ($this->thumb) {
                $dimen = explode('x', $this->thumb);
                $descPath = $this->getSavePath($data);
                $imginfo = $this->crop($data['upload_data']['pic_path'], $this->thumb, $descPath, FALSE);
                $data['upload_data']['file_name'] = $this->hashKey . "_" . $this->thumb . $data['upload_data']['file_ext'];
                $data['upload_data']['width'] = $dimen[0];
                $data['upload_data']['height'] = $dimen[1];
            }

            if ($this->create) {
                $file_path = $data['upload_data']['file_path'];
                $file_c_name = $this->hashKey . '_c.jpg';
                $file_z_name = $this->hashKey . '_z.jpg';

                $this->crop($data['upload_data']['pic_path'], $this->create, $file_path . $file_c_name, FALSE, 1);
                $imginfo = $this->crop($data['upload_data']['pic_path'], SQUARE, $file_path . $file_z_name, FALSE, 1);
            } else {
                $imginfo = $data['upload_data']['pic_path'];
            }

            if (empty($imginfo)) {
                return Constants::$IMAGE_PROCESS_ERROR;
            } else {
                $width = $data['upload_data']['width'];
                $height = $data['upload_data']['height'];
                $ret['id'] = $this->hashKey;
                $ret['url'] = FILE_DOMAIN . $this->hashFolder . $data['upload_data']['file_name'];
                $ret['width'] = (isset($width) && is_numeric($width)) ? $width : 0;
                $ret['height'] = (isset($height) && is_numeric($height)) ? $height : 0;
                return $ret;
            }
        }
    }

    public function resize() {

        $suffixName = ".jpg";
        $picInfo = array_pop($this->paths);
        $picinfo_array = explode('_', $picInfo);
        $key = $picinfo_array[0];
        if (stristr($key, '.')) {
            $keyArr = explode('.', $key);
            $key = $keyArr[0];
        }
        

        //判断原图是否存在
        $path = FileHelper::getFilePath(FILE_FOLDER, $this->prefixFolder, $key);

        if ($path) {
            if (!empty($picinfo_array[1]) && isset($picinfo_array[1])) {
                $sizeArr = explode('.', $picinfo_array[1]);
                $sizes = $sizeArr[0];
                if (self::validateIMageSize($sizes)) {
                    $this->crop($path . $key . $suffixName, $sizes, $path . $picinf . $suffixName);
                } else {
                    HttpHelper::sendException(Constants::$IMAGE_SIZE_ERROT);
                }
            } else {
                HttpHelper::cache_send($path . $key . $suffixName);
            }
        } else {
            HttpHelper::sendException(Constants::$IMAGE_NOT_EXISTS);
        }
    }

    private function validateIMageSize($size) {
        $allowCropIMageSizes = Constants::$ALLOW_CROP_IMAGE_SZIES;
        return true;
    }

    private function getSavePath($data) {
        return $data['upload_data']['file_path'] . $this->hashKey . "_" . $this->thumb . $data['upload_data']['file_ext'];
    }

    public function compact($file) {
        
    }

    private function crop($sourcePath, $sizes, $newPath, $isShow = TRUE, $type = 2) {
        if (!class_exists('MyImageCrop')) {
            include('MyImageLib.php');
        }
        $size = explode('x', $sizes);
        $img = new MyImageCrop($sourcePath, $newPath);
        $imginfo = $img->Crop($size[0], $size[1], $type);
        if ($isShow) {
            HttpHelper::cache_send($newPath, filemtime($newPath));
        } else {
            $imginfo['new_path'] = $newPath;
            return $imginfo;
        }
    }

}
