# Paste Bin

## SUMMARY
An app that allows users to share content and provide a link to be able to access the shared content

### Setup
#### DEPENDENCIES
1. JAVA 11
2. MYSQL
3. GRADLE
4. SPRING BOOT

#### START UP
1. Download Java 11 and install 
2. Download Mysql 8.0.25 and install 
3. Create DataBase pasteBin
4. Open a cmd or terminal and go to the root directory of the project
5. Create the environmental variables for the db connections, the required ones are as follows 
   1. ``content_datasource_url``
   2. ``content_datasource_username``
   3. ``content_datasource_password``
   4.  ``content.domain.url``
6. The default for the ``content.domain.url`` is ``http://localhost:8090/api/``
7. run the project using the following command <br>
   ``gradle springboot:run``

### API DOCUMENTATION
Endpoint: **api/create** <br>
Method: **POST**

**Request Parameters:**

**text** (required): The text content to be shared. <br>
**expiry** (optional): The expiry date and time for the content in a format like yyyy-MM-dd-HH-mm-ss. If not provided, the content expire in 1 hour. <br>

**Response:**

The API will respond with a short URL that can be used to access the shared content.

Endpoint: **api/{url}** <br>
Method: **GET**

**Path Variable:**
**url** (required): The unique id on the url given to fetch the shared content


**Response:**

The API will respond with the shared content.