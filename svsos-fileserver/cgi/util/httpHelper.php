<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class HttpHelper {

    public static function sendException($exceptionData) {
        echo json_encode($exceptionData);
        exit;
    }

    public static function sendImage($content) {
        header("Content-type: image/jpeg");
        echo $content;
        exit;
    }

    public static function cache_show($dir, $filename) {
        $firstFolder = substr($filename, 0, 1);
        $secondFolder = substr($filename, 1, 2);
        $lastFolder = substr($filename, 3, 2);
        $path = $dir . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $filename;
        if (file_exists($path)) {
            self::cache_send($path);
        }
    }

    public static function cache_send($file, $contentType = 'image/jpeg') {
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
        header("Content-type: " . $contentType);
        echo file_get_contents($file);
        exit;
    }

}
