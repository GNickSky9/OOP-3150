#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <fstream>

std::ofstream outputfile("output.txt");

void print_reverse(const std::string &sentence, int iteration)
{
    std::vector<std::string> words;
    std::stringstream stream(sentence);

    outputfile << "Case #" << iteration << ": ";

    for(std::string word; stream >> word;)
        words.push_back(word);

    for(std::vector<std::string>::reverse_iterator it = words.rbegin(); it != words.rend(); ++it)
        outputfile << *it << " ";
}

int main()
{
    std::string sentence;
    std::string fileName;
    std::string word;
    int cases = 0;
	int cnt = 1;

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
		getline(streamIn,sentence);
		print_reverse(sentence,cnt);
		outputfile << "\n";
		++cnt;
	}

    return 0;
}

