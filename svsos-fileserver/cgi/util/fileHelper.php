<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class FileHelper {

    public static function path_as_array($path) {
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
    public static function uuid($more = true, $len = 5) {
        if ($more) {
            return uniqid(md5(mt_rand()), true);
        } else {
            return uniqid(self::create_guid_section($len), false);
        }
    }

    public static function create_guid_section($characters) {
        $return = '';
        for ($i = 0; $i < $characters; $i++) {
            $return .= dechex(mt_rand(0, 15));
        }
        return $return;
    }

    public static function getFilePath($diskfolder, $prefixFolder = "", $key, $url = FALSE, $fileext = '.jpg') {

        $firstFolder = substr($key, 0, 1);
        $secondFolder = substr($key, 1, 2);
        $lastFolder = substr($key, 3, 2);
        $path = $diskfolder . $prefixFolder . "/" . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $key . $fileext;
        if ($url) {
            return FILE_DOMAIN . $prefixFolder . '/' . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/' . $key . $fileext;
        }

        if (file_exists($path)) {
            return $diskfolder . $prefixFolder . "/" . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/';
        } else {
            return false;
        }
    }

    /**
     * 获取图片所在文件夹
     *
     * @return
     */
    public static function getHashFolder($prefixFolder = "", $hashKey = "") {
        $firstFolder = substr($hashKey, 0, 1);
        $secondFolder = substr($hashKey, 1, 2);
        $lastFolder = substr($hashKey, 3, 2);

        return $prefixFolder . "/" . $firstFolder . '/' . $secondFolder . '/' . $lastFolder . '/';
    }

    /**
     *
     * 根据用户ID取整和取余，拼装图片存放目录
     *
     */
    public static function getHashFolderByUid($uid, $modNum = 1000) {

        $firstFolder = (int) ($uid / $modNum);
        $secondFolder = $uid % $modNum;

        return $firstFolder . '/' . $secondFolder . '/';
    }

    /**
     * 创建服务器存储的目录结构
     *
     * @return
     */
    public static function createServerFolder($filePath = "") {
        mkdir($filePath, 0755, true);

        return $filePath;
    }

}
