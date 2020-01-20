Step to Run Application
-----------------------
1)	Import 'product' project as maven project. Build project.
2)	Run ProductApplication.java file as ‘Java Application’.

Appilcation is using H2 database.

To call Insert product API
--------------------------
Make POST Request to 
Url: http://localhost:8080/v1/products

Playload: {
"name": "blue Shirt",
"description": "Red hugo boss shirt",
"brand": "Hugo Boss",
"tags": [
"red",
"shirt",
"slim fit"
],
"category": "apparel"
}


To call Search product API
--------------------------
Make GET request to 
Url:http://localhost:8080/v1/products/search?category={CATEGORY_VALUE}&pageNumber={PAGE_NUMBER}&pageSize={PAGE_SIZE}

Parameters:
category: category value need to be searched in the database.
pageNumber: page number to be fetched, it start from 0. First page corresponds to value 0.
pageSize: number of items to be retrieved on the page

EXAMPLE :http://localhost:8080/v1/products/search?category=apparel&pageNumber=0&pageSize=2
Here are searching "apparel" category and retrieving First page with two items.

Testing
-------
Use Test runner: Junit4 for running integration tests
