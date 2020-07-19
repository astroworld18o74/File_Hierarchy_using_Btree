#pragma once
#include "btree.hpp"

//for any OS we can add , delete ,search files
class OS{
public:
    BTree os_btree;
    OS(){
        //every new OS will have it's btree object
        os_btree = BTree(3);
    }
    //operations on the file
    void add_file(string, double);
    BTreeNode * search_file(string , double);
    void show_files();
    void  remove(File );
};


void OS :: add_file(string name, double size){
    File temp=File(name , size);
    os_btree.insert(temp);
}

BTreeNode* OS::search_file(string name, double size){
    File temp = File(name, size);
    return os_btree.search(temp);
}

void OS::show_files(){
    os_btree.traverse();
}

void OS::remove(File file){
    os_btree.remove(file);
}
