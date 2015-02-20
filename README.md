# BusWars

Basic REST service demo on an in memory data base using Grails 2.4.4.

Get Item Inventory URI 
GET /inventory/{sku}
Response Body { sku: ‘123’, name: ‘Body Armor’, price: 123.45, qty: 10 }

Status Codes 200: OK 404: Not Found

Buy Item URI
POST /inventory/{sku}
Request Body { sku: ‘123’, qty: 3, price: 1.23, all-or-none: true }
Reponse Body { sku: ‘123’, name: ‘Body Armor’, qty-purchased: 3, price: 1.23, total: 3.69, trans-id: 879284 }

Status Codes 200: OK ?: failure codes

