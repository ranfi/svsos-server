<?php

class cutimg extends displayer{

    protected $hashKey;
    protected $cutPath;

    public function __construct()
	{
		parent::__construct();
		session_start();
		$this->do_cutimg();
	}

    protected function do_cutimg()
    {
        $parameter = $_REQUEST;
        //系统头像
        if( !empty($parameter['s']) || (strpos($parameter['s'],'s') === 0 ) )
        {
            $avatarPath  = WANBAN_FOLDER.'s/ss/ss/'.$parameter['s'].'.png';
            $json = array('result'=>'succeed','callback'=>'编辑成功','avatarpath'=>$avatarPath,'key'=>$parameter['s']);
        }
        //用户自定义头像
        else
        {
            $filepath  = $_SESSION['fullpath'];
            $result    = $this->imageCut($parameter,$filepath);
            if($result == true)
            {
                $json = array('result'=>'succeed','callback'=>'编辑成功','key'=>$this->hashKey,'avatarpath'=>$this->cutPath.'?'.time());
            }
            else
            {
                $json = array('result'=>'fail','callback'=>'图片编辑异常，请确认后再试');
            }
        }

        echo $parameter['callback'].'('.json_encode($json).')';
    }

    /**
     * 对图片进行切割处理
     *
     * @param mixed $parameter
     * @param mixed $filepath
     * @return
     */
    private function imageCut($parameter,$filepath)
    {
        $config['image_library'] = 'gd2';
        $config['source_image'] = $filepath;
        $config['create_thumb'] = TRUE;
        $config['maintain_ratio'] = TRUE;

        //当用户没有编辑头像尺寸  自动为用户设置
        if($parameter['w'] == 0 && $parameter['z'] == 0)
        {
            if(empty($filepath) || !preg_match('/^.*(.jpg|.JPG|.gif|.GIF|.png|.PNG|.jpeg|.JPEG)$/',$filepath))
            {
                return false;
            }
            $size = getimagesize($filepath);
            if($size['0'] < $size['1'])
            {
                $parameter['w'] = $size['0'];
                $parameter['z'] = $size['0'];
            }
            else
            {
                $parameter['w'] = $size['1'];
                $parameter['z'] = $size['1'];
            }

        }
        if (!class_exists('Image_lib')) include('libraries/Image_lib.php');
        $img_lib = new Image_lib();
        $this->hashKey = $_SESSION['hashkey'];
        session_destroy();
        $fileFolder = $this->getHashFolder();
        $matches = null;
        preg_match('/^.*(.jpg|.JPG|.gif|.GIF|.png|.PNG|.jpeg|.JPEG)$/',$filepath,$matches);
        $config['new_image'] = WANBAN_FOLDER.$fileFolder.$this->hashKey.$matches[1];
        $config['width']  = 100;
        $config['height'] = 100;
        $config['x_axis'] = $parameter['x'];
        $config['y_axis'] = $parameter['y'];
        $config['orig_width'] = $parameter['w'];
        $config['orig_height'] = $parameter['z'];
        $img_lib->initialize($config);
        if ( ! $img_lib->crop())
        {
            return false;
        }
        $this->cutPath = WANBAN_FOLDER.$fileFolder.$this->hashKey.$matches[1];
        $img_lib->clear();

        return true;
    }
}