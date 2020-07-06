#pragma once
#include <iostream>

class File{
public:
    std::string file_name = "";
    double file_size = 0;

    File(){}
    File(std::string name, double size):file_name(name), file_size(size){}

    //normal file functions
    void rename();
    void change_size();
};
