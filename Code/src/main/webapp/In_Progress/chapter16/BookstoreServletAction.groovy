html.html()
{
    head()
    {
        title 'Processing Values'
    }
    body()
    {
        String first = request.getParameter('firstName');
        String last = request.getParameter('lastName');
 
        h1 'Hello ${first} ${last}'
        ht1("Hello ${first} ${last}")
    }
}