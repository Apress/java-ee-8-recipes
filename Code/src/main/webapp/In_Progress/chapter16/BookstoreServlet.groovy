import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:oracle:thin:@host:1521:database", "user",
        "password", "oracle.jdbc.driver.OracleDriver")

html.html()
{
    head()
    {
        title 'Acme Bookstore Book List'
    }
    body()
    {
        print '<p>The list of books:<br/><br/>'
        sql.eachRow("select * from Book"){
            println "$it.title <br/>"
        }
        print '</p>'
        p 'This is another line'
        p 'and another'
        
        p {
            b 'this is bold'
            br{}
            s 'this is strong'
            br{}
            i 'this is italicized'    
        }
        
    }
}