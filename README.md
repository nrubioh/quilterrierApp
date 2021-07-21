# Proyecto Quilterrier
___

El avance a presentar el día de hoy corresponde a una aplicación llamada Quilterrier la cual tiene por objetivo, ser un centro de ayuda para la búsqueda de mascotas perdidas, adopción de animales y tenencia responsable de los mismos. Por esta razon se pretende incluir en la misma, las siguientes caracteristicas (escalables y con el objetivo de incentivar la busqueda de información y el desarrollo de nuevas conocimientos y habilidades entre los integrantes del grupo).

![1](https://i.imgur.com/rwbajUE.png)
![2](https://i.imgur.com/almUwsr.png)
![3](https://i.imgur.com/DkyMj6M.png)
![4](https://i.imgur.com/3FM0Fwl.png)

Dentro de estas funcionalidades, para esta primera entrega decidimos centrarnos en cosas nuevas no vistas en clases, por lo que se trabajó principalmete 5 areas:

1. Sistema de registro de cuentas, Login y __Firebase__.
2. Recycle View. 
3. Incorporación de mapas a la aplicación.
4. Interfaz gráfica
5. Github

Estos puntos se detallan a continuación:

## ___Login:___

Para la aplicación se tiene pensado utlizar un sistema de Login (soportado en Google Firebase), con el fin de diferenciar entre usuarios que puedan solamente ver el contenido de la app y los que puedan ver y realizar comentarios o solicitudes de búsqueda o avisar sobre mascotas encontradas. 
 
Así, con esto en mente, el primer paso fue la implementación inicial del sistema de Login como se presenta a continuación (imagenes ilustrativas no representan la apariciencia final de la aplicación, son solo un modo de represtar visualmente el avance y el código de la misma):

![imagencuadruple](https://i.imgur.com/ZuerAy7.png)

Estas 4 activitys fueron codificadas y se dejaron en funcionamiento, permitiendo crear cuentas, loguearse con ellas, realizar restableciemito de contraseña y desloguearse si así se necesita.

El código y el readme en detalle de esta puede encontrarse los siguientes enlaces:

+ [Código](https://github.com/nrubioh/quilterrierApp/tree/RamaLeo/QuilTerrier_Java/app/src/main/java/com/example/quilterrier)
+ [Readme en detalle](https://github.com/nrubioh/quilterrierApp/tree/RamaLeo/Markdown%20Login)
  
Importante el señalar que a futuro se pretende incluir funcionalidades como login con Facebook, Twitter y Google, pero por ahora solo está disponible el login por correo y contraseña.

## ___Recycle View:___

Mediante esta parte de la app se busca mostrar en orden a las macotas que serán dadas en adopción y aquellas que estan perdidas, de forma que los usuario registrados puedan verlas y publicar (a futuro) los comentarios y avisar sobre mascotas encontradas. La forma en que esto se llevó a cabo se explica a continuación:

+ ___Main Activity:___ Se dispuso realizar un landing que incluyera un RecylcerView con los últimas mascotas perdidas o en adopción. 

![recyclerview-diagram](https://i.imgur.com/X02d3Or.png)

Como podemos observar, se deben Implementar:
* DataSet
* Adapter
* RecylcerView

### Data Set
Al ser un mock up, se implementó de la siguiente manera:

Agregamos algunos <string-array> con los diferentes perros/gatos, su clasificación, su ubicación y si es perro o gato.

```xml
<resources>
    <string name="app_name">QuilTerrier</string>

    <string-array name="nombre_perro">
        <item>Jhon</item>
        <item>Geralt</item>
        <item>Goku</item>
        <item>Ruben</item>
        <item>Mara</item>
    </string-array>

    <string-array name="tipo">
        <item>Perdido!</item>
        <item>Adopcion</item>
        <item>Perdido!</item>
        <item>Perdido!</item>
        <item>Adopción</item>
    </string-array>

    <string-array name="ubicacion">
        <item>Caldera</item>
        <item>Los Nogales</item>
        <item>Independencia</item>
        <item>Maipu</item>
        <item>Valparaíso</item>
    </string-array>
    <string-array name="especie">
        <item>Perro</item>
        <item>Perro</item>
        <item>Gato</item>
        <item>Perro</item>
        <item>Perro</item>
    </string-array>

</resources>

```   

### Adapter

Se creó una clase que extiende de RecyclerView.Adapter y necesita nuestro propio viewHolder, en este caso lo llamamos MyViewHolder. A este se le asocian los elementos de la UI, como botones, textview, etc.
Hay dos cosas importantes a destacar:
* Utilizamos el **```LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item```**. Este marca que se debe *inflar* el layout Row item, que solo tiene una Cardview, y esta será reproducida n veces siendo n la cantidad de datos.

![rowitem](https://i.imgur.com/IPbhhyX.jpg)

* **``onBindViewHolder(@NonNull MyViewHolder holder, int position)``** implica que vamos a unir(bind), el holder(MyViewHolder) con la posición de la *"lista"*. Ahí explicitamente le estamos informando que en función al holder, defina, texto, color, e incluso imagenes.


```java
public class AdapterRecylcer extends RecyclerView.Adapter<AdapterRecylcer.MyViewHolder> {
    String name[], type[], location[], especieAnimal[];
    int images[];
    Context context;


    public AdapterRecylcer(Context ctx, String nombre[], String tipo[], String ubicacion[], String especieArr[],
                           int img[]) {
        context = ctx;
        name = nombre;
        type = tipo;
        location = ubicacion;
        images = img;
        especieAnimal = especieArr;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, tipo, ubicacion;
        ImageView imageView_animal, especie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_animal = itemView.findViewById(R.id.imageView_animal);
            nombre = itemView.findViewById(R.id.textView_nombre);
            tipo = itemView.findViewById(R.id.textView_tipo);
            ubicacion = itemView.findViewById(R.id.textView_ubicacion);
            especie = itemView.findViewById(R.id.especie);
        }
    }

```

Esto traducido en las vistas de Android se expresa de la siguiente manera para el usuario:
</br>
</br>
![asiempezo](https://i.imgur.com/UwBaChA.png)

Primeros intentos (así comenzo)
</br>
</br>
![asitermino](https://i.imgur.com/eSmmZ6U.jpg)

Version de prueba (así termino)
</br>
</br>

## ___Incorporación de mapas a la aplicación:___

En esta version de la aplicacion, se planeo implementar los mapas donde hubo extravio de las mascotas, por lo cual se estudio el tema con respecto a la adherencia de ubicación en la aplicación. Para esto si bien no esta del todo implementado esta parte en la presente entrega, si podemos decir que los mapas fueron utilizados pero solucionar el problema de la localización fina con el GPS 

## ___Interfaz gráfica:___

Se trabajo en la interfaz que verá el usuario final de cara a la aplicación, en esta se busca que sea facil de utilizar, llamativa (reflejado en el color de los botones), amena (ya que se condireando la posible perdida de la mascota, que para gran parte de las personas es parte de la familia), para esto utilizamos una paleta de tonalidades suaves para darle a entender al usuario que será una herramienta de apoyo en esos tiempo dificiles. La tipografía, sencilla y clara (Quicksand light y Quicksand bold fueron las ocupadas), todo esto acompañado de imagenes simples que muestren el uso de la aplicación en pos de ayudar a las mascotas.

## ___Github:___

Para poder tener un uso eficiente de Github asi como un mayor entendimiento de la plataforma creamos un solo repositorio y de ese repositorio fuimos creando ramas en base al desarrollo de las tareas asignadas a cada participante del proyecto. Esto nos permitio mejor comprension al momento de unir las ramas dentro de la rama principal y así trabajar como un grupo profesional con la plataforma.

[Enlace a nuestro repositorio](https://github.com/nrubioh/quilterrierApp)


![reposit](https://i.imgur.com/yqFAMCA.png)
