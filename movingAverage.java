import java.util.ArrayList;

class ExamException extends Exception
{
    public ExamException(String message)
    {
        super(message);
    }
}


class MovingAverage
{
    private int window_size;

    public MovingAverage(int ws) throws ExamException
    {

        if (ws<=0) throw new ExamException("Window size must be a positive number.");
        this.window_size = ws;
    }

    ArrayList<Double> compute(ArrayList<Double> data) throws ExamException
    {
        if (data.size() == 0) throw new ExamException("The provided data list is empty.");
        else if (data.size() < this.window_size) throw new ExamException("The window size is bigger than the provided data list.");

        int length = data.size() - this.window_size + 1;
        ArrayList<Double> result = new ArrayList<Double>(length);
        double m;

        for (int i=0;i<length;i++)
        {
            m=0;
            for(int j=0;j<this.window_size;j++)
            {
                m+=data.get(i+j);
            }

            result.add(m / (double) this.window_size);
        }

        return result;
    }

    public static void main(String[] args)
    {
        double array_data [] = {2,4,8,16};
        ArrayList<Double> data = new ArrayList<Double>(array_data.length);
        ArrayList<Double> result;

        for (double d : array_data)
        {
            //data.add(d);
        }

        try
        {
            MovingAverage moving_average = new MovingAverage(2);

            result = moving_average.compute(data);

            System.out.print("[");

            for (int i = 0;i<result.size();i++)
            {
                if (i!=0) System.out.print(",");
                System.out.print(result.get(i));
            }

            System.out.println("]");
        }
        catch(ExamException e)
        {
            System.out.println(e.getMessage());
        }

    }
}

