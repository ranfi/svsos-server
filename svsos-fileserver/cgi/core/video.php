<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
include_once('cgi/core/uploadFile.php');
include_once('cgi/core/fileProcess.php');
include_once('cgi/util/fileHelper.php');
include_once('cgi/util/httpHelper.php');

class Video extends UploadFile implements FileProcess {

    private $paths;
    private $query;
    private $prefixFolder = "video";
    private $videoPath;

    public function __construct($path, $query) {
        $this->paths = FileHelper::path_as_array($path);
        $this->query = $query;
        parent::__construct($props = array());
    }

    public function doUpload() {
        if (!empty($_FILES)) {
            //音频文件上传
            $data = array();
            $flag = TRUE;
            foreach ($_FILES as $filename => $v) {
                $this->hashKey = FileHelper::uuid(false);
                $this->hashFolder = FileHelper::getHashFolder($this->prefixFolder, $this->hashKey);
                $this->videoPath = FILE_FOLDER . $this->hashFolder;
                $data[] = $this->_upload($filename);
            }
            echo json_encode($data);
        } else {
            HttpHelper::sendException(Constants::$UPLOAD_FILE_NOT_EMPTY);
        }
    }

    private function _upload($filename) {
        if (is_dir($this->videoPath)) {
            $userUploadFolderPath = $this->videoPath;
        } else {
            $userUploadFolderPath = FileHelper::createServerFolder($this->videoPath);
        }

        $config['upload_path'] = $userUploadFolderPath;
        $config['allowed_types'] = 'mp4';
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
            $ret['id'] = $this->hashKey;
            $ret['url'] = FILE_DOMAIN . $this->hashFolder . $data['upload_data']['file_name'];
            return $ret;
        }
    }

    public function resize() {
        
    }

    public function compact($file) {
        
    }

}
