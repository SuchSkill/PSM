//package task_03_pendulum;
//
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Pixmap;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.math.Circle;
//import com.badlogic.gdx.math.Vector2;
//
//public class MyGdxGame extends ApplicationAdapter {
//    SpriteBatch batch;
//    Texture bg;
//    ShapeRenderer shapeRenderer;
//    private double dt=0.2;//временная еденица
//    private final double ang=45.0f;//начальный угол наклона маятника
//    private double alfa;//угол для ейлера
//    private double omega;//центростремительная сколрость для ейлера
//    private final  double radius =200.0;//длинна нити
//    private final float mass=1.5f;//масса шарика
//    private final double g= 9.80665;// сила замного притяжения
//    private double omega2;//for midPoint
//    private double alfa2;//for midPoint все остальное для рисования
//    private Vector2 []ej=new Vector2[640];
//    private int pos=0;
//    private float startX=250;
//    private float startY=700;
//    private float yMid=300;
//    private int posx=0;
//    private int poss=0;
//    private int posxx=0;
//    private Vector2 midd[]=new Vector2[640];
//    private Vector2 [] ekEj=new Vector2[640];
//    private Vector2 [] ekMid=new Vector2[640];
//    private int ekEjpos=0;
//    private int ekPosx=0;
//    private int ekEjposs=0;
//    private int ekPosxx=0;
//
//    @Override
//    public void create () {//создание сцены
//        batch = new SpriteBatch();
//        bg = new Texture("bg1280_720_2.jpg");
//        double xx=Math.PI/180.0;
//        omega=0.0;
//        alfa=(180.0-ang)*xx;
//        omega2=0.0;
//        alfa2=(180.0-ang)*xx;
//        shapeRenderer = new ShapeRenderer();
//    }
//
//
//    public void drawXY(double alfaa,float stY,boolean b){//метод для нахождения и рмсования положения шарика
//        double x1 = radius *Math.sin(alfaa);	// wyznaczenie x polozenia kulki
//        double y1 = radius *Math.cos(alfaa);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.BLACK);
//        float fx1=(float)x1+startX;
//        float fy1=(float)y1+stY;
//        shapeRenderer.line(startX, stY,fx1,fy1);
//        shapeRenderer.end();
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.circle(fx1-1, fy1-1, 32);
//        shapeRenderer.end();
//        eP(y1,b);//вызов метода для потенциальной энергии
//
//
//    }
//    public void Euler(){//метод который считает методом ейлера
//        alfa = alfa + omega * dt;
//        omega= omega + (g/ radius)*Math.sin(alfa)*dt;
//    }
//    public void midPoint(){//метод который считает методом мидпоинт
//        double out2 = (g/ radius)*Math.sin(alfa2);
//        double t1=alfa2+omega2*dt/2.0;
//        double t2=omega2+out2*dt/2.0;
//        double k2=(g/ radius)*Math.sin(t1);
//        omega2 =omega2+k2*dt;
//        alfa2=alfa2+t2*dt;
//    }
//
//    public void eP(double y,boolean b){ //метод который считает потенциальную энергию
//        double h = radius + y + 0.5;//началиные координаты для ейлера(верхний) (640,360)
//        double ep = mass * g * h;//потенциальная энегрия, дальше идет код для рисования
//        if(b) {//для ейлера
//            if (pos != 639) {
//                ej[pos] = new Vector2(posx + 640, (float) ep / 3 + 360);
//                pos++;
//                posx++;
//            } else {
//                posx = 0;
//                pos = 0;
//                ej = new Vector2[640];
//            }
//            drawCurve2(shapeRenderer, ej,1);
//            ek(omega,b,ep);//вызываем метод для кинетической энергии для ейлера
//        }else{//для мидпоинт
//            if (posx != 639) {
//                midd[poss] = new Vector2(posxx + 640, (float) ep / 3+10);//началиные координаты для мидпоинт(нижний) (640,0)
//                poss++;
//                posxx++;
//            } else {
//                posxx = 0;
//                poss = 0;
//                midd = new Vector2[640];
//            }
//            drawCurve2(shapeRenderer, midd,1);
//            ek(omega2,b,ep);//вызываем метод для кинетической энергии для мидпоинт
//        }
//
//
//    }
//    private Vector2 [] eCe=new Vector2[639];
//    private Vector2 [] eCm=new Vector2[639];
//    public void ek(double om,boolean b,double cal){//метод который считает кинетическую энергию
//        double mi=mass*(om*om* radius * radius)/2.0;//кинетическая энергия
//
//        cal+=mi;//целая энергия, дальше код для рисования
//        if(b) {
//            if (ekEjpos != 639) {
//                ekEj[ekEjpos] = new Vector2(ekPosx + 640, (float) mi / 3 + 360);
//                eCe[ekEjpos]=new Vector2(ekPosx + 640,(float)cal/3+360);
//                ekEjpos++;
//                ekPosx++;
//            } else {
//                ekPosx = 0;
//                ekEjpos = 0;
//                ekEj = new Vector2[640];
//                eCe=new Vector2[640];
//            }
//            drawCurve2(shapeRenderer, ekEj,0);
//            drawCurve2(shapeRenderer,eCe,2);
//        }else{
//            if (ekEjposs != 639) {
//                ekMid[ekEjposs] = new Vector2(ekPosxx + 640, (float) mi / 3+10);//началиные координаты для мидпоинт(нижний) (640,0)
//                eCm[ekEjposs]=new Vector2(ekPosxx + 640,(float)cal/3+10);
//                ekEjposs++;
//                ekPosxx++;
//            } else {
//                ekPosxx = 0;
//                ekEjposs = 0;
//                ekMid = new Vector2[640];
//                eCm=new Vector2[640];
//            }
//            drawCurve2(shapeRenderer, ekMid,0);
//            drawCurve2(shapeRenderer,eCm,2);
//        }
//
//    }
//    @Override
//    public void render () {//метод который вызывается 60 раз в секунду
//
//        Gdx.gl.glClearColor(1, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(bg, 0, 0);
//
//        batch.end();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.BLACK);
//        shapeRenderer.line(0,360,1280,360);
//        shapeRenderer.line(640,0,640,720);
//        shapeRenderer.end();
//
//
//        Euler(); //start euler
//        drawXY(alfa,startY,true);//end euler
//
//
//        midPoint();//start midPoint
//        drawXY(alfa2,yMid,false); //end Midpoint
//
//
//
//    }
//
//    /** Рисует кривую на основании множества точек. Рисует без сглаживания.
//     * В текущей реализации массивом точек выступает массив pointsArr
//     */
//    public void drawCurve2(ShapeRenderer shapeRenderer, Vector2[] pointsArr,int b){
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        for (int i = 0; i < pointsArr.length-1; ++i) {
//            if(pointsArr[i]!=null && pointsArr[i+1]!=null){
//                if(b==0){
//                    shapeRenderer.setColor(Color.BROWN);
//                }else if(b==1){
//                    shapeRenderer.setColor(Color.GREEN);
//                }else{
//                    shapeRenderer.setColor(Color.VIOLET);
//                }
//                shapeRenderer.line(pointsArr[i], pointsArr[i + 1]);
//                shapeRenderer.setColor(Color.BLACK);
//                shapeRenderer.point(pointsArr[i].x, pointsArr[i].y, 0);
//            }
//        }
//        shapeRenderer.end();
//
//    }
//    @Override
//    public void dispose () {
//        batch.dispose();
//        bg.dispose();
//    }
//}
