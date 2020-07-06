#include <iostream>
#include "os.hpp"
#include "file.hpp"

int main(){
    OS windows;
    windows.add_file("sahil.txt", 1000);
    windows.add_file("ram.txt", 2000);
    windows.add_file("aditi.txt", 1001);
    windows.add_file("sumati.txt", 2001);

    windows.show_files();
    File temp = File("sahil.txt", 1000);
    if (windows.search_file(temp.file_name, temp.file_size) != NULL)
        std::cout << "Present" << std::endl;
    else
        std::cout << "Not Present" << std::endl;

    return 0;
}
