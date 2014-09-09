<?php
class photo extends displayer{
	
	private $paths;
	private $query;
	
	public function __construct($path, $query)
	{
		parent::__construct();
		$this->paths = $this->path_as_array($path);
		$this->query = $query;
		$this->display();
	}
	
	public function display()
	{
		$picinfo = array_pop($this->paths);
		
		//直接输出缓存图片
		//$this->cache_show(IMAGE_FOLDER, $picinfo);
		
		$picinfo_array = explode('_',$picinfo);
		$key = $picinfo_array[0];
		if ( stristr($key, '.') )
		{
			$keyArr = explode('.', $key);
			$key = $keyArr[0];
		}
		
		//判断原图是否存在
		$path = $this->getFilePath(IMAGE_FOLDER, $key);

		if ( $path )
		{
			if ( !empty($picinfo_array[1]) && isset($picinfo_array[1]) )
			{
				$sizeArr = explode('.', $picinfo_array[1]);
				$sizes = $sizeArr[0];
				switch ( $sizes )
				{
					case '100x100':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo.".jpg");
					break;
					
					case '150x150':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo.".jpg");
					break;
				
					case '56x56':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo.".jpg");
					break;
				
					case '96x96':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo.".jpg");
					break;
					
					case '128x128':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo.".jpg");
					break;
				
					case '640x360':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo.".jpg");
					break;
				
					case '620x620':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo.".jpg");
					break;	
				
					default:
						//$this->cache_send($path.$key.'.jpg');
						die('图片的尺寸不合法');
				}
			}
			else
			{
				$this->cache_send($path.$key.'.jpg');
			}
		}
		else
		{
			die('图片不存在');
		}
		
	}
	
	
	
}