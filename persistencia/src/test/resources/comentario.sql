insert into producto(codigo, nombre, precio, fecha_limite, disponibilidad) values (1, "Carro de juguete", 30000.0, "2021/12/30", 40);
insert into producto(codigo, nombre, precio, fecha_limite, disponibilidad) values (2, "Figura de acción", 40000.0, "2021/12/25", 33);
insert into producto(codigo, nombre, precio, fecha_limite, disponibilidad) values (3, "Peluche de oso", 45000.0, "2021/12/10", 22);
insert into producto(codigo, nombre, precio, fecha_limite, disponibilidad) values (4, "Carro de juguete", 30000.0, "2021/12/31", 40);

insert into categoria values (1, "Tecnología");
insert into categoria values(2, "Electrodomestico");
insert into categoria values(3, "Deporte");
insert into categoria values(4, "Mascotas");

insert into categoria_productos values(1,1);
insert into categoria_productos values(2,1);
insert into categoria_productos values(3,1);
insert into categoria_productos values(4,1);
insert into categoria_productos values(2,2);
insert into categoria_productos values(4,3);
insert into categoria_productos values(2,3);

insert into Comentario(codigo, mensaje, calificacion, fecha_comentario, codigo_producto_codigo) values (1, "Me gusta muy poco el diseño", 2,"2021/10/31 19:00:12", 1);
insert into Comentario(codigo, mensaje, calificacion, fecha_comentario, codigo_producto_codigo) values (2, "¿Cuánto cuesta?", 3,"2021/10/31 15:19:45", 2);
insert into Comentario(codigo, mensaje, calificacion, fecha_comentario, codigo_producto_codigo) values (3, "Muy buen producto", 5,"2021/10/29 16:28:32", 3);
insert into Comentario(codigo, mensaje, calificacion, fecha_comentario, codigo_producto_codigo) values (4, "Me gusta muy poco el diseño", 4,"2021/10/31 19:00:12", 1);
insert into Comentario(codigo, mensaje, calificacion, fecha_comentario, codigo_producto_codigo) values (5, "¿Cuánto cuesta?", 2,"2021/10/31 15:19:45", 2);
insert into Comentario(codigo, mensaje, calificacion, fecha_comentario, codigo_producto_codigo) values (6, "Muy buen producto", 1,"2021/10/29 16:28:32", 3);

insert into Comentario(codigo, mensaje, calificacion, fecha_comentario,codigo_producto_codigo,respuesta) values (7, "Me gusta muy poco el diseño", 2,"2021/10/31 19:00:12",1,"Gracias");
insert into Comentario(codigo, mensaje, calificacion, fecha_comentario,codigo_producto_codigo) values (8, "¿Cuánto cuesta?", 3,"2021/10/31 15:19:45",1);
insert into Comentario(codigo, mensaje, calificacion, fecha_comentario,codigo_producto_codigo,respuesta) values (9, "Muy buen producto", 5,"2021/10/29 16:28:32",1,"Gracias");