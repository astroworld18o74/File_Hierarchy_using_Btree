#include <iostream>
#include "os.hpp"
#include "file.hpp"

int main(){
    OS windows;
    int n;
    do{
        cout<<"\nEnter the operation you want to perform:";
        cout<<"\n1 : Insert a file";
        cout<<"\n2 : Delete a file";
        cout<<"\n3 : Search a file";
        cout<<"\n4 : List all the files";
        cout<<"\n0 : Exit";
        cout<<endl;
        
        cin>>n;
        
        if(n==1){
            string filename;
            cout<<"Enter file name:";
            cin>>filename;
            double filesize;
            cout<<"Enter the file size:";
            cin>>filesize;
            if(windows.search_file(filename,filesize)==NULL)
                windows.add_file(filename,filesize);
            else cout<<"\nThis file is already present."<<endl;
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
            cout<<"\nThe file with filename "<<filename<<" and filesize "<<filesize<<" is ";
            if(windows.search_file(filename,filesize)!=NULL){
                cout<<"present."<<endl;
            }
            else cout<<"not present."<<endl;
        }
        else if(n==4){
            cout<<"\nAll the files in ascending order are:\n";
            windows.show_files();
        }
        else return 0;
    }while(n<5 && n>0);
    return 0;
}
