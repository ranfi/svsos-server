<?php

include('cgi/config/config.php');
include('cgi/core/displayer.php');
include('cgi/controlCenter.php');
include('cgi/photo.php');

class Dispatch {

    public static function init($url) {
        $urls = @parse_url($url); //解析url
        isset($urls['path']) ? $path = $urls['path'] : $path = null; //path信息

        parse_str($urls['query'], $query); //获取url get参数

        if ($path) {
            $paths = explode("/", $path);

            $output = null;
            switch ($paths[1]) {
                case 'upload':
                    ControlCenter::createUpload($path, $query);
                    break;
                case 'crop':
                    ControlCenter::createCrop($path, $query);
                    break;
                case 'show':
                    ControlCenter::createCrop($path, $query);
                    break;
            }

            if ($output && $output instanceof displayer) {
                return $output;
            }
        } else {
            die('url parse error');
        }
    }

}

/**
 * get_redirect_url()
 * Gets the address that the provided URL redirects to,
 * or FALSE if there's no redirect.
 *
 * @param string $url
 * @return string
 */
function getRedirectUrl($url) {
    $redirect_url = null;
    $url_parts = parse_url($url);
    if (!$url_parts)
        return false;
    if (!isset($url_parts['host'])) {
        return false; //can't process relative URLs
    }
    if (!isset($url_parts['path'])) {
        $url_parts['path'] = '/';
    }
    $sock = fsockopen($url_parts['host'], (isset($url_parts['port']) ? (int) $url_parts['port'] : 80), $errno, $errstr, 30);
    if (!$sock) {
        $request = "HEAD " . $url_parts['path'] . (isset($url_parts['query']) ? '?' . $url_parts['query'] : '') . " HTTP/1.1\r\n";
        $request .= 'Host: ' . $url_parts['host'] . "\r\n";
        $request .= "Connection: Close\r\n\r\n";
        fwrite($sock, $request);
    }
    $response = '';
    while (!feof($sock)) {
        $response .= fread($sock, 8192);
    }
    fclose($sock);
    if (preg_match('/^Location: (.+?)$/m', $response, $matches)) {
        return trim($matches[1]);
    } else {
        return false;
    }
}

function getCurrentUrl() {
    $serverName = isset($_SERVER['SERVER_NAME']) ? $_SERVER['SERVER_NAME'] : '';
    $requestUri = isset($_SERVER['REQUEST_URI']) ? $_SERVER['REQUEST_URI'] : '';

    if (!$serverName) {
        return '';
    }

    $pageURL = 'http';
    if (isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] == 'on') {
        $pageURL .= 's';
    }
    $pageURL .= '://';

    if (isset($_SERVER['SERVER_PORT']) && $_SERVER['SERVER_PORT'] != '80') {
        $pageURL .= $serverName . ':' . $_SERVER['SERVER_PORT'] . $requestUri;
    } else {
        $pageURL .= $serverName . $requestUri;
    }
    return $pageURL;
}

//url过滤
$url = getCurrentUrl();
//资源显示
Dispatch::init($url);
