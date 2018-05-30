from org.python.core.FutureFeature import with_statement
from com.ziclix.python.sql import zxJDBC
from javax.servlet.http import HttpServlet


jdbc_url = "jdbc:oracle:thin:@host:1521:database"
username = "user"
password = "password"
driver = "oracle.jdbc.driver.OracleDriver"

class BookstoreJython (HttpServlet):

    def doGet(self, request, response):
        self.doPost(request, response)

    def doPost(self, request, response):
        response.setContentType("text/html")
        out = response.getWriter()
        htmlbody = """<h1>Acme Bookstore</h1> 
                      <br/>
                      <p>List of books: """ + """</p>"""
        with zxJDBC.connect(jdbc_url, username, password, driver) as conn:
            with conn:
                with conn.cursor() as c:
                    c.execute("select title from book")
                    books = c.fetchall()
                    for book in books:
                        htmlbody = htmlbody + """%s <br/>""" % (book,)
        out.println(self.convertHtml('Acme Bookstore', htmlbody))

    def convertHtml(self, title, info):
        return """<html>
                      <head><title> """ + title + """ </title></head>
                      <body> """ + info + """</body>
                  </html>"""

    def getServletInfo(self):
        return "This servlet returns a list of books in the Acme Bookstore"