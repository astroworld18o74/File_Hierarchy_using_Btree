#include<bits/stdc++.h>
using namespace std;

class File{
public:
    string file_name;
    double file_size;

    //Constructor
    File(string name, double size){
        file_name=name;
        file_size=size;
    }
    //default constructor for file class
    File(){
        file_name="";
        file_size=0;
    }
    //normal file functions
    void rename();
    void change_size();
};

//for any OS we can add , delete ,search files
class OS{
    //create a btree for every OS
public:
    BTree os_btree;
    //BTreeNode os_btreenode;
    OS(){
        //every new OS will have it's btree object
        os_btree=BTree();
        //os_btreenode=BTreeNode();
    }
    //operations on the file
    void add_file(string, double);
    void search_file(string, double);
    void show_files();
};


void OS :: add_file(string name, double size){
    File temp=File(name , size);
     
    //insert that file to the btree of this os
    os_btree.insert(temp);
}


void OS :: search_file(string name,double size){
    File temp=File(name,size);

    os_btree.search(temp);
}

void OS :: show_files(){
    os_btree.traverse();
}

//A BTreeNode
class BTreeNode{
    File *keys; // An array of files
    int t;// Minimum degree(defines the range for number of keys)
    BTreeNode **C;//An array of child pointers
    int n; //Current number of keys
    bool leaf;//is true when node is leaf. Otherwise false
public:
    BTreeNode(int _t,bool leaf);//Constructor
    
    //An utility function to insert a new key in thw subtree rooted with this node.
    //The assumption is ,the node must be non-full when this
    //function is called
    void insertNonFull(File);

    //An utility function to split the child y of this node. i is index of y in
    //child array C[].The child must be full when this function is called.
    void splitChild(int i, BTreeNode *y);

    //A function to traverse all nodes in a subtree rooted with this node
    void traverse();

    //A function to search a file in the subtree rooted  with this node.
    BTreeNode *search(File);// returns NULL if k is not present.

    //Make BTree friend of this so that we can access the private mwmbers of this \
    //class in BTree functions
    friend class BTree;
};


// A BTree
class BTree{
    BTreeNode *root;  //Pointer to root node
    int t; //Minimum degree

public:
    //Constructor (initialises tree as empty)
    BTree(int _t){root=NULL; t=_t;}
    
    //default constructor for BTree class
    BTree(){root=NULL; t=0;}

    //function to search a file in this tree
    BTreeNode * search(File k){
        return (root==NULL)?NULL :root->search(k);
    }

    //The main function that inserts a new file in this B-tree
    void insert(File k);
};

//Constructor for BTreeNode class
BTreeNode :: BTreeNode(int t1, bool leaf1){

    //copy the given minimum degree and leaf property
    t=t1;
    leaf=leaf1;

    //Allocate memory for maximum number of possible keys
    //and child pointers
    keys=new File[2*t-1];
    C=new BTreeNode *[2*t];

    //Initialise the number of fileas as 0
    n=0;
}

//Function to traverse all nodes in a subtree rooted with this node
void BTreeNode :: traverse(){
    
    //There are n fileas and n+1 children,traverse through n files
    // and first n children
    int i;
    for(int i=0;i<n;i++){

        //If this is not leaf, then before printing key[i],
        //traverse the subtree rooted with child C[i].
        if(leaf==false){
            C[i]->traverse();
        }
        
        cout<<" " << keys[i].file_name;
    }
        //Print the subtree rooted with last child
        if(leaf==false)
            C[i]->traverse();
}

//Function to search key k in subtree rooted with this node
BTreeNode * BTreeNode :: search(File file){
    //find the first file having size greater than or equal to k
    int i=0;
    while(i<n && file.file_size > keys[i].file_size) i++;

    int k=i;
    //if found key is same as file ,return this node 
    if(keys[i].file_name==file.file_name) return this;

    //if key is not found here then travere till the file has same size
    while(i<n && file.file_size==keys[i].file_size){
        if(file.file_name==keys[i].file_name)return this;
        i++;
    }
    int kk=i;

    int j=k;
    while(j<kk){
        if(C[i]->search(file)!=NULL)return C[i]->search(file);
        j++;
    }

    //if it is leaf node and the value of file size is less than the next file sizes
    if(leaf==true && j>=k) return NULL;
}

//The main function that inserts a new key in this B-Tree
void BTree :: insert(File file){
    //if tree is empty
    if(root==NULL){
        //Allocate memory for new root
        root=new BTreeNode(t,true);
        root->keys[0]=file; //insert file
        root->n=1;//update number of files in root
    }
    else{ //if tree is not empty
        
        //if root is full, then tree grows in height
        if(root->n==2*t-1){
            //Allocate memory for new root
            BTreeNode *s= new BTreeNode(t,false);

            //Make old root as child of new root
            s->C[0]=root;

            //Split the old root and move 1 key to the new root
            s->splitChild(0,root);

            //new root has two children now. Decide which of the
            //two children is going to have new key
            int i=0;
            if(s->keys[0].file_size<file.file_size) i++;

            s->C[i]->insertNonFull(file);

            //change root
            root=s;
        }
        else{ //if root is not full, call insertNonFull for root
            root->insertNonFull(file);
        }
    }
}

// A utility function to insert a new key in this node
// The assumption is, the node must be non-full when this
//function is called
void BTreeNode :: insertNonFull(File file){
    
    //Initialise index as index of rightmost element
    int i=n-1;

    //if this is lead node
    if(leaf==true){
        //the following loop does two things
        // a. find the location of new key to be inserted
        // b. moves all greater keys to one place ahead
        while(i>=0 && keys[i].file_size > file.file_size){
            keys[i+1]=keys[i];
            i--;
        }

        //insert the new key at found location
        //if there are multiple keys or files of the same size then this
        //function inserts the new key at the end of all those keys
        keys[i+1]=file;
        n=n+1;
    } 
    else{ // if this is not leaf node
        
        //find the child which is going to have the new key
        while(i>=0 && keys[i].file_size>file.file_size)i--;

        // see if the found child is full
        if(C[i+1]->n == 2*t-1){
            // if the child is full the split it
            splitChild(i+1,C[i+1]);

            //after split, the middle file of C[i] ges upa nd 
            //C[i] is splitted into two. See which of the two 
            // is going to have the new key
            if(keys[i+1].file_size<file.file_size)i++;
        }
        C[i+1]->insertNonFull(file);
    }
}

//A utility function to split the child y of this node
// note that y must be full when this function is called
void BTreeNode :: splitChild(int i, BTreeNode *y){

    //Create a new node which is going ot store (t-1) files/keys
    //of y
    BTreeNode *z=new BTreeNode(y->t,y->leaf);
    z->n=t-1;

    //copy the last (t-1) keys of y to z
    if(y->leaf == false)
        for(int j=0;j<t;j++)
            z->C[j]=y->C[j+t];
    
    //Reduce the number of files/keys in y
    y->n=t-1;

    //Since this node is going to have a new child,
    //create a space of new child 
    for(int j=n; j>=i+1; j--)
        C[j+1]=C[j];

    //Link the new child to this node
    C[i+1]=z;

    //A key of y will move to this node. Find the location of
    // new keys and move all greater keys one space ahead
    for(int j=n-1;j>=i;j--) keys[j+1]=keys[j];

    //copy the middle key of y to this node
    keys[i]=y->keys[t-1];

    //increment count of keys in this node
    n+=1;
}

int main(){
    OS windows;
    windows.add_file(sahil.txt,1000);
    windows.add_file()
    return 0;
}
