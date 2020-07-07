#include <iostream>
#include "os.hpp"
#include "file.hpp"

int main(){
    OS windows;
    /*windows.add_file("sahil.txt",1000);
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
    */
    int n;
    do{
        cout<<"Enter the operation you want to perform:\n";
        cout<<"1 : Insert a file\n";
        cout<<"2 : Delete a file\n";
        cout<<"3 : Search a file\n";
        cout<<"4 : Traverse a file\n";
        cout<<"0 : Exit\n";
        cout<<endl;
        
        cin>>n;
        
        if(n==1){
            string filename;
            cout<<"Enter file name:";
            cin>>filename;
            double filesize;
            cout<<"Enter the file size:";
            cin>>filesize;
            windows.add_file(filename,filesize);
        }
        
        else if(n==2){
            string filename;
            cout<<"Enter file name: ";
            cin>>filename;
            double filesize;
            cout<<"Enter the file size: ";
            cin>>filesize;
            File file;
            file.file_name=filename;
            file.file_size=filesize;
            windows.remove(file);
        }
        else if(n==3){
            string filename;
            cout<<"Enter file name:";
            cin>>filename;
            double filesize;
            cout<<"Enter the file size:";
            cin>>filesize;
            cout<<"The file with filename "<<filename<<" and filesize "<<filesize<<" is ";
            if(windows.search_file(filename,filesize)!=NULL){
                cout<<"present."<<endl;
            }
            else cout<<"not present."<<endl;
        }
        else if(n==4){
            cout<<"All the files in ascending order are:\n";
            windows.show_files();
        }
        else return 0;
    }while(n<5 && n>0);
    return 0;
}
