#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

int cntFlips(string str)
{
    int cnt = 0;
    vector<char> stack1;
    char temp;
    for(int i = 0; i < str.size(); ++i)
    {
        if(!stack1.empty())
        {
            temp = stack1.back();
            if(temp != str[i])
            {
                for(int j = 0; j < stack1.size(); ++j)
                {
                    stack1[j] = str[i];
                }
                ++cnt;
            }
        }
        stack1.push_back(str[i]);      
    }
    if(stack1.back() == '-')
    {
        for(int j = 0; j < stack1.size(); ++j)
        {
            stack1[j] = '+';
        }
        ++cnt;
    }
    
    return cnt;
}

int main()
{
    ifstream inp("large.txt");
    ofstream out("output.txt");
    if(!inp)cout << "ERROR";
    int cases = 0;
    int cnt = 1;
    inp >> cases;
    inp.ignore();
    string pancakes;
    
    while(!inp.fail() && cnt != cases+1)
    {
        out << "Case #" << cnt << ": ";
        inp >> pancakes;
        inp.ignore();
        out << cntFlips(pancakes);
        if(cnt != cases)
            out << "\n";
        ++cnt;
    }
    
    return 0;
}
