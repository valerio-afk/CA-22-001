#include <iostream>
#include <vector>
#include <string>

using namespace std;

class ExamException : public exception
{
    private:
        string message;
    public:
        ExamException(string msg) : message(msg) {}

        string what()
        {
            return this->message;
        }
};


class MovingAverage
{
    private:
        unsigned int window_size;

    public:
        MovingAverage(unsigned int ws) : window_size(ws) 
        {
            if (ws==0) throw(ExamException("Window size must be a positive number."));
        }

        vector<double> compute(vector<double> data)
        {
            if (data.size() == 0) throw ExamException("The provided data list is empty.");
            else if (data.size() < this->window_size) throw ExamException("The window size is bigger than the provided data list.");

            unsigned int length = data.size() - this->window_size + 1;
            vector<double> result(length);
            double m;

            for (int i=0;i<length;i++)
            {
                m=0;
                for(int j=0;j<this->window_size;j++)
                {
                    m+=data[i+j];
                }

                result[i] = m / (double) this->window_size;
            }

            return result;
        }
};


int main(int argc, const char**argv)
{
    try
    {
        double array_data [] = {2,4,8,16};
        vector<double> data(array_data, array_data+sizeof(array_data) / sizeof(double));
        vector<double> result;

        MovingAverage moving_average(2);

        result = moving_average.compute(data);

        cout<<"[";

        for (int i = 0;i<result.size();i++)
        {
            if (i!=0) cout<<",";
            cout<<result[i];
        }

        cout<<"]"<<endl;
    }
    catch(ExamException e)
    {
        cout<<e.what()<<endl;
    }

    return 0;
}