#include <iostream>
#include "os.hpp"
#include "file.hpp"

int main(){
    OS windows;
    windows.add_file("sahil.txt",1000);
    windows.add_file("karan.txt",1000);
    windows.add_file("ram.txt",2000);
    windows.add_file("aditi.txt",1001);
    windows.add_file("sumati.txt",2001);
    
    File file;
    file.file_name="sahil.txt";
    file.file_size=1000;
    
    windows.remove(file);
    cout<<"Files in ascending order:\n";
    windows.show_files();
    File temp=File("sahil.txt",1000);
    if(windows.search_file(temp.file_name,temp.file_size)!=NULL) cout<<"\nPresent"<<endl;
    else cout<<"Not Present"<<endl;
    
    return 0;
}
