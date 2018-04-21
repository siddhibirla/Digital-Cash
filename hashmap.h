#include <iostream>
#include <vector>
class hashmap
{
private:
int tablesize;
struct hash
{
std::string name;
std::string dateofbirth;
hash* next;
};
std::vector<hash*> v ;
public:
hashmap();
int Create_hash(std::string name);
void print();
void update();
};
