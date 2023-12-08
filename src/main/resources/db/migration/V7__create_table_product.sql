CREATE TABLE tb_product (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    price DECIMAL NOT NULL,
    stock INT NOT NULL,
    image VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

INSERT INTO `tb_product` (id, name, price, stock, image, description)
VALUES
  (
    "ceb22d57-4311-4d41-898b-8e4c690c2019",
    "White Shoes",
    19.99,
    100,
    "https://images.pexels.com/photos/1670766/pexels-photo-1670766.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    "Introducing our Classic White Shoes – the epitome of timeless style and versatility. Crafted with precision and attention to detail, these shoes are a wardrobe essential for any fashion enthusiast. The pristine white color effortlessly complements a variety of outfits, from casual to semi-formal, making them the perfect choice for any occasion. With a comfortable fit and durable construction, our white shoes not only look good but also provide all-day comfort. Step out in confidence, embracing a clean and sophisticated look that never goes out of fashion with our Classic White Shoes."
  ),
  (
    "3c9d07d1-f5bc-4596-aca1-4ca1e006580a",
    "Green Shoes",
    29.99,
    200,
    "https://images.pexels.com/photos/1619652/pexels-photo-1619652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    "Introducing our Green Harmony Shoes, a stylish and eco-friendly footwear choice for the environmentally conscious individual. These sleek and comfortable shoes are designed with a vibrant shade of green, adding a touch of nature-inspired flair to your wardrobe. Crafted from sustainable materials, Green Harmony Shoes not only make a fashion statement but also contribute to a greener planet. Step into a world of style and sustainability with these trendy green shoes that seamlessly blend fashion and environmental responsibility. Walk with purpose, walk in Green Harmony Shoes."
  ),
  (
    "8a83447e-b70e-4dfd-815c-5f26f713a9e1",
    "Yellow Shoes",
    39.99,
    300,
    "https://images.pexels.com/photos/1598508/pexels-photo-1598508.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    "Introducing our vibrant Yellow Shoes – a stylish and eye-catching footwear choice for those who want to make a bold statement with their fashion. These shoes are not just a pop of color; they're a mood lifter and a symbol of individuality. Crafted with comfort and style in mind, our yellow shoes are perfect for adding a sunny touch to your outfit, whether you're strolling through the city streets or stepping out for a casual event. Step into a world of style and confidence with our Yellow Shoes – where fashion meets sunshine."
  );
