#pragma once
#include <bits/stdc++.h>

class File{
public:
    string file_name;
    double file_size;

    File(string name, double size){
        file_name=name;
        file_size=size;
    }
    
    File(){
        file_name="";
        file_size=0;
    }
};

