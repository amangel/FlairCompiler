program RecursiveFactorial (in : integer);
    function factorial(n : integer) : integer
    begin
        if n <= 1 then
            return n
        else 
            return n * factorial(n - 1)
    end;
begin
    print(factorial(in))
end.
