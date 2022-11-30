open System

exception ExamException of string

type MovingAverage(ws: int) =
   let window_size : int = ws
   do
      if window_size <= 0 then raise ( ExamException "Window size must a non-zero positive number")

   member this.compute (data : float[]) : float[] =
      if (data.Length = 0) then raise ( ExamException "The provided data list is empty")
      elif (data.Length < window_size) then raise (ExamException ("The window size is bigger than the provided data list"))
      else Array.init (data.Length - window_size + 1) (fun idx -> (Array.sum(data.[idx .. (idx + window_size-1)]) / float window_size))
      
let input: float[] = [|2;4;8;16|]


printfn "%A" (MovingAverage(2).compute input)