<?php

include("cgi/config/constant.php");
include('cgi/crop.php');
include("cgi/util/fileHelper.php");
include("cgi/core/image.php");
include("cgi/core/audio.php");
include("cgi/core/video.php");

/**
 * 控制中心，所有的业务分发将从这里开始
 */
class ControlCenter {

    public static function createUpload($path, $query) {
        $fileProcess = NUll;
        $ftype = $query['ftype'];
        switch ($ftype) {
            case "img" : $fileProcess = new Image($path, $query);
                break;
            case "audio" :$fileProcess = new Audio($path, $query);
                break;
            case "video": $fileProcess = new Video($path, $query);
                break;
            default : $fileProcess = new Image($path, $query);
                break;
        }
        $fileProcess->doUpload();
    }

    public static function createCrop($path, $query) {
        $image = new Image($path, $query);
        $image->resize();
    }

}
