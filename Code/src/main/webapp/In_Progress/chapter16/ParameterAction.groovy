

html.html()
{
    head()
    {
        title 'Acme Bookstore Groovy Parameters'
    }
    body()
    {
       
        form(method: 'GET', action: 'BookstoreServletAction.groovy') {
            b'First Name: '
            input(type: 'text', name:'firstName')
            br{}
            b'Last Name: '
            input(type: 'text' ,name:'lastName') 
            br{}
            input(type: 'submit', value:'Submit Values')
        }
    }
}