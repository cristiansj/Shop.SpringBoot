insert into producto(codigo, nombre, precio, fecha_limite, disponibilidad) values (1, "Carro de juguete", 30000.0, "2021/12/30", 40);
insert into producto(codigo, nombre, precio, fecha_limite, disponibilidad) values (2, "Figura de acción", 40000.0, "2021/12/25", 33);
insert into producto(codigo, nombre, precio, fecha_limite, disponibilidad) values (3, "Peluche de oso", 45000.0, "2021/12/10", 22);

insert into categoria values (1, "Tecnología");
insert into categoria values(2, "Electrodomestico");
insert into categoria values(3, "Deporte");
insert into categoria values(4, "Mascotas");

insert into categoria_productos values (1,1);
insert into categoria_productos values (2,1);
insert into categoria_productos values (4,1);
insert into categoria_productos values (1,2);
insert into categoria_productos values (2,2);
insert into categoria_productos values (4,2);
insert into categoria_productos values (3,3);
insert into categoria_productos values (4,3);
insert into categoria_productos values (2,3);

insert into subasta values (1, "2022/12/13 19:15:14", 3);
insert into subasta values (2, "2022/12/05 16:20:24", 1);
insert into subasta values (3, "2022/12/05 17:34:51", 3);
insert into subasta values (4, "2020/12/05 18:19:52", 3);
insert into subasta values (5, "2020/12/05 16:38:59", 2);
insert into subasta values (6, "2020/12/05 22:27:36", 1);
