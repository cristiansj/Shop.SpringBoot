insert  into Ciudad values (1,"Armenia");
insert  into Ciudad values (2,"Pereira");
insert  into Ciudad values (3,"Medellín");

insert  into Usuario values (1,"Tatiana@email.com","Tatiana","123",1);
insert  into Usuario values (2,"Eliana@email.com","Eliana","123",2);
insert  into Usuario values (3,"Mariana@email.com","Mariana","123",3);

insert into producto(codigo, nombre, codigo_vendedor_codigo, precio, fecha_limite, disponibilidad) values (1, "Carro de juguete", 1, 30000.0, "2021/12/30", 40);
insert into producto(codigo, nombre, codigo_vendedor_codigo, precio, fecha_limite, disponibilidad) values (2, "Figura de acción", 2, 40000.0, "2021/12/25", 33);
insert into producto(codigo, nombre, codigo_vendedor_codigo, precio, fecha_limite, disponibilidad) values (3, "Peluche de oso", 3, 45000.0, "2021/12/10", 22);

insert into chat(codigo, codigo_producto_codigo) values (1, 1);
insert into chat(codigo, codigo_producto_codigo) values (2, 2);
insert into chat(codigo, codigo_producto_codigo) values (3, 1);
insert into chat(codigo, codigo_producto_codigo) values (4, 3);
insert into chat(codigo, codigo_producto_codigo) values (5, 3);
insert into chat(codigo, codigo_producto_codigo) values (6, 1);