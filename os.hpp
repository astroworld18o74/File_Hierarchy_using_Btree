#pragma once
#include "btree.hpp"

class OS{
    //create a btree for every OS
public:
    BTree os_btree;
    //BTreeNode os_btreenode;
    OS(){
        //every new OS will have it's btree object
        os_btree = BTree(3);
        //os_btreenode=BTreeNode();
    }
    //operations on the file
    void add_file(string, double);
    BTreeNode *search_file(string name, double size){
        File temp = File(name, size);

        return os_btree.search(temp);
    };
    void show_files();
};

void OS ::add_file(string name, double size){
    File temp = File(name, size);

    //insert that file to the btree of this os
    os_btree.insert(temp);
}

void OS ::show_files(){
    os_btree.traverse();
}
