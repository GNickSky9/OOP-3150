#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

void isSleep(const string &str, bool arr[])
{
    for(int i = 0; i < str.size(); ++i)
    {
        if(str[i]=='0')
            arr[0] = true;
        else if(str[i]=='1')
            arr[1] = true;
        else if(str[i]=='2')
            arr[2] = true;
        else if(str[i]=='3')
            arr[3] = true;
        else if(str[i]=='4')
            arr[4] = true;
        else if(str[i]=='5')
            arr[5] = true;
        else if(str[i]=='6')
            arr[6] = true;
        else if(str[i]=='7')
            arr[7] = true;
        else if(str[i]=='8')
            arr[8] = true;
        else if(str[i]=='9')
            arr[9] = true;
    }
    
    return;
}

int main()
{
    ifstream data("large.txt");
    ofstream answers("output.txt");
    if(!data)
    {
        cout << "ERROR OPENING INPUT FILE!";
        return -1;
    }
    int cases = 0;
    data >> cases;
    data.ignore();
    long long int numb = 0;
    int cnt = 1;
    long long int prev = 0;
    string copy;
    stringstream conv;
    bool arr[10];
    bool status = false;
    
    while(!data.fail() && cnt != cases+1)
    {      
        for(int i = 0; i < 10; ++i)
            arr[i] = false;
        data >> numb;
        prev = numb;
        data.ignore();
        answers << "Case #" << cnt << ": ";

        if(numb == 0)
        {
            answers << "INSOMNIA";
        }
        else
        {
            while(status == false)
            {
                conv << numb;
                copy = conv.str();
                isSleep(copy,arr);
                if(arr[0]==true){
                    if(arr[1]==true){
                        if(arr[2]==true){
                            if(arr[3]==true){
                                if(arr[4]==true){
                                    if(arr[5]==true){
                                        if(arr[6]==true){
                                            if(arr[7]==true){
                                                if(arr[8]==true){
                                                    if(arr[9]==true){
                                                        answers << numb;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                numb += prev;
            }
        }
        if(cnt != cases)
            answers << "\n";
        ++cnt;
        status = false;
        conv.str(string());
        conv.clear();
    }
    
    return 0;
}
