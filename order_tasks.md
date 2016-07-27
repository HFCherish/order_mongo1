#tasks (242-3.95h :: 227-3.78h)
2. product creation
	2. should return 201 when create a product  (resource.post)  --6 :: 5
	3. should contain creation uri in header location (resource.post) --3 :: 2
	4. should 400 when the product param is not complete --6 :: 8
	4. --------------------------------------------------
	3. should have tried to save the creation data into database and should able to get that product after creation. (repo.save, repo.findById) --7 :: 8
	4. should that one product's _id is the same as the created one (resourc.post, repo.save, mapper, database, records; repo.findById, mapper) --12 :: 10
	4. should the creation uri contains product _id in header location -- 2 :: 2
6. get some product
	6. should return 200 when get some product (resource.get) --5 :: 4
	7. should the response body contains right uri, name, description, price info (resource.get) --6 :: 3
	3. --------------------------------------------------
	4. should return 404 when no products in db --1 :: 1
6. get all products
	1. should return 200 when get products (resource.get) --2 :: 2
	3. --------------------------------------------------
	2. should have tried to search from database and should able to get a least one product if database is not empty (repo.findAll) --3 :: 2
	3. should that one product's _id are the same as expected (mapper-findAll) --3 :: 2
	3. --------------------------------------------------
	2. should the response body contains at least  one item info and contains right uri, name, description, price info (resource.get, record) --3 :: 2
11. user register
	11. should return 201 when register a new user (resource.post) --4 :: 4
	12. should return 400 when the registered name is null or not composed of letters and numbers, at least one (resource.post) -- 9 :: 4
	13. should include creation uri in header location (resource.post) --5 :: 2
	3. --------------------------------------------------
	14. should have tried to save the user info into database and should able to get a user after registered (repo.findById) --6 :: 6
	16. should the user's _id is the same as the saved one. (resourc.post, repo.save, mapper, database, record; repo.findById, mapper) --6 :: 9
	3. -------------------------------------------------
	4. should the creation uri contains user _id in header location -- 2 :: 2
11. get one user
    1. should 200 when get some user --5 :: 5
    1. should contains right uri, name, _id info when get user --5 :: 4
    1. should 404 when user doesn't exist --1 :: 1
18. order creation
	18. should return 201 when creating an order (resource.post) --7 :: 8
	19. should include the creation uri in header location (resource.post) --3 :: 3
	3. --------------------------------------------------
	20. should have tried to save the order info into database and should able to get the order after created order (repo.findById) --6 :: 5
	22. should the order's _id is the same as the created one (resourc.post, repo.save, mapper, database, record; repo.findById, mapper) --10 :: 11
	3. --------------------------------------------------
	4. should the creation uri contains order _id in header location -- 1 :: 1
	2. should return 400 when the input doesn't contain name, address, phone --4 :: 5
	4. should return 400 when the order contains 0 order item or the item doesn't exist, or the item's product_id, quantity is null --4 :: 5
	1. should return 400 when the order item _id is invalid --5 :: 5 (1h stucked)
27. get some order of some user
	28. should return 200 when review some order of some user (resource.get) --6 :: 6
	29. should the response body contain right uri info (resource.get) --8 :: 4
 	20. should the response body contain right name, address, phone,  created\_at info (resource.get, record) --4 :: 3
 	21. should the response body contain at least one order_item info (resource.get, record) --18 :: 19
 	22. should the order\_item contain right product_id, quantity, amount info (resource.get, record) --5 :: 3
 	1. 20. should the response body contain right total\_price info (resource.get, record) --3 :: 3
	4. should return 404 when no order in db --1 :: 1
27. get all orders of some user
	28. should return 200 when review all orders of some user (resource.get) --2 :: 2
	3. --------------------------------------------------
	29. should have tried to fetch all orders from database and should get one order when there's one order in database and order's _id is the same as the created one (resourc.post, repo.save, mapper, database, record; repo.findAll, mapper) --2 :: 12
	3. --------------------------------------------------
	29. should the response body contain at least one order info and right uri, name, address, phone, total\_price info (resource.get, record) --2 :: 2
	1. should the response body doesn't contain order item info -- 2
29. create payment
	30. should return 201 when pay (resource.post) --6 :: 7
	1. should 400 when input doesn't contain pay_type, amount -- 3 :: 2
	3. --------------------------------------------------
	31. should try to save the payment info into database and should able to get that payment after pay (resourc.post, repo.findByOrderId) --5 :: 3
	32. should the order _id of that payment is as expected (resourc.post, repo.save, mapper, database, record; repo.findByOrderId, mapper) --11 :: 10
34. get payment of some order
	32. should return 200 when get payment (resourc.get) --3 :: 6
	35. should the response body include right pay_type, amount, order_uri & payment uri (resource.get) --9 :: 7
	34. should the response body include right creating date (resoure.get, mapper-findByOrderId, record) --4 :: 4
	4. should return 404 when no payment in db --1 :: 1

#databases
1. product: 
	2. _id
	2. name
	3. description
	4. price
2. order:
	3. 	_id
	1. user_id
	3. name
	4. address
	5. phone
	7. created_at
	9. pay_state
7. order_items:
	8. order_id
	8. product_id
	9. quantity
	10. amount
10. user:
	11. _id
	11. name
12. payments:
	13. order_id
	8. pay_type
	10. pay_at
	11. pay_amount


	



