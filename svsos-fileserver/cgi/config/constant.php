<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Constants {

    public static $UPLOAD_FILE_NOT_EMPTY = array("error_code" => 9001, "error_msg" => "upload file cannot be empty!");
    public static $IMAGE_SIZE_UNDEFINED = array("error_code" => 9002, "error_msg" => "cannot set the image  size.");
    public static $UNKNOW_ERROR = array("error_code" => 9003, "error_msg" => "upload has an exception occured.");
    public static $UPLOAD_FILE_ERROR = array("error_code" => 9004, "error_msg" => "upload file has an exception occured.");
    public static $NOT_EMPTY = array("error_code" => 9005, "error_msg" => "User id cannot be empty!");
    public static $IMAGE_PROCESS_ERROR = array("error_code" => 9006, "error_msg" => "picture process has an exception occured.");
    public static $IMAGE_NOT_EXISTS = array("error_code" => 9007, "error_msg" => "image not exists.");
    public static $IMAGE_SIZE_ERROT = array("error_code" => 9008, "error_msg" => "image size error.");
    //允许裁剪的图片尺寸
    public static $ALLOW_CROP_IMAGE_SZIES = array("100x100", "200x200");

}
