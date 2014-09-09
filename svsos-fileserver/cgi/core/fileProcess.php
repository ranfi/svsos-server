<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

interface FileProcess {

    public function doUpload();

    public function compact($file);

    public function resize();
}
