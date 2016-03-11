#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <numeric>
#include <fstream>

template <class iter>
void descend_sort(iter beg, iter end)
{
    std::sort(beg,end,std::greater<int>());
}

template <class iter>
void ascend_sort(iter beg, iter end)
{
    std::sort(beg,end);
}

template <class container>
long long int findSmallestScalarProduct(container x, container y)
{
    long long int zero = 0;

    if(x.size() != y.size())
    {
        std::cout << "Containers Are Not Same Length!\n";
        throw -1;
    }

    ascend_sort(x.begin(),x.end());
    descend_sort(y.begin(),y.end());

    return std::inner_product(x.begin(),x.end(),y.begin(),zero);
}

int main()
{
    std::ofstream outputfile("outputScalar.txt");
    std::vector<long long int> vec1, vec2;
    std::string fileName;
    int cases = 0;
    int cnt = 1;
    int vecLength = 0;
    long long int numb = 0;

    std::cout << "Enter Input File Name: ";
    getline(std::cin,fileName);

    std::ifstream streamIn;
    streamIn.open(fileName);

    if(!streamIn)
    {
        std::cout << "Error Opening File.\n";
        return -1;
    }

    streamIn >> cases;
    streamIn.ignore();

    while(!streamIn.fail() && cnt != cases+1)
    {
        streamIn >> vecLength;
        streamIn.ignore();
        for(int i = 0; i < vecLength; ++i)
        {
            streamIn >> numb;
            vec1.push_back(numb);
        }
        streamIn.ignore();
        for(int i = 0; i < vecLength; ++i)
        {
            streamIn >> numb;
            vec2.push_back(numb);
        }
        outputfile << "Case #" << cnt << ": " << findSmallestScalarProduct(vec1,vec2) << "\n";
        vec1.clear();
        vec2.clear();
        streamIn.ignore();
        ++cnt;
    }

    return 0;
}
