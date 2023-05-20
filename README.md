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
4. 

### API DOCUMENTATION
Endpoint: **/create** <br>
Method: **POST**

**Request Parameters:**

**text** (required): The text content to be shared. <br>
**expiry** (optional): The expiry date for the content in a format like YYYY-MM-DD. If not provided, the content expire in 1 hour. <br>

**Response:**

The API will respond with a short URL that can be used to access the shared content.

Endpoint: **/{url}** <br>
Method: **GET**

**Path Variable:**
**url** (required): The unique id on the url given to fetch the shared content


**Response:**

The API will respond with the shared content.