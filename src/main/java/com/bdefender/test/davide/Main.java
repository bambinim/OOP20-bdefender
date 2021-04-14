package com.bdefender.test.davide;

import com.bdefender.Pair;
import com.bdefender.enemies.EnemyBase;
import com.bdefender.enemies.EnemyFactory;
import com.bdefender.enemies.pool.EnemiesPoolImpl;
import com.bdefender.enemies.pool.EnemiesPoolMover;
import com.bdefender.enemies.view.EnemiesViewLoader;
import com.bdefender.map.Coordinates;
import com.bdefender.map.Map;
import com.bdefender.map.MapLoader;
import com.bdefender.tower.Tower;
import com.bdefender.tower.TowerFactory;
import com.bdefender.tower.view.TowerViewLoader;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class Main extends Application {



	 public static void main(String[] args) {
	 	Application.launch();
	 }

	@Override
	public void start(Stage primaryStage) throws Exception {

		Map map = MapLoader.getInstance().loadMap(Map.COUNTRYSIDE);
		EnemiesPoolImpl pool = new EnemiesPoolImpl(new MapInteractorImpl(map));

		EnemyFactory eFactory = new EnemyFactory();

		pool.addEnemy(eFactory.getEnemy1(pool.getSpawnPoint()));
		pool.addEnemy(eFactory.getEnemy2(pool.getSpawnPoint()));

		TowerFactory tFactory = new TowerFactory();
		Tower tz1 = tFactory.getTowerZone1(pool, new Pair<>(8.0,12.0));
		Tower tz2 = tFactory.getTowerZone2(pool, new Pair<>(15.0,0.0));

		ImageView tz1Image = new ImageView();
		if (TowerViewLoader.GetTowerImage(tz1).isPresent()) {
			tz1Image.setImage(TowerViewLoader.GetTowerImage(tz1).get());
			Coordinates towerPos = new Coordinates(tz1.getPos().getX(),tz1.getPos().getY() );
			tz1Image.setX(towerPos.getLeftPixel());
			tz1Image.setY(towerPos.getTopPixel());
		}

		Thread eThread = new EnemiesThread(pool);
		//Thread tThread2 = new TowerThread(tz2);
		//tThread2.start();

		primaryStage.setTitle("Map");
		Pane root = new Pane();
		root.setMaxWidth(1280);
		root.setMaxHeight(736);
		Canvas canvas = new Canvas(1280,1280);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().addAll(map, canvas, tz1Image);
		primaryStage.setResizable(true);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		Thread tThread1 = new TowerThread(tz1, root);
		tThread1.start();
		eThread.start();
		EnemyViewThread eViewThread = new EnemyViewThread(pool.getEnemies(),gc);
		eViewThread.start();
	}

	static class EnemyViewThread extends Thread {
	 	private List<EnemyBase> enemies;
	 	private GraphicsContext gc;

	 	public EnemyViewThread(List<EnemyBase> enemies, GraphicsContext gc){
			this.enemies = enemies;
			this.gc = gc;
		}


		@Override
		public void run(){
	 		while (true){
				try {
					sleep(10L);
					HashMap<EnemyBase, Optional<Image>> enemiesImage = EnemiesViewLoader.GetEnemiesImages(enemies);
					gc.clearRect(0, 0, 1280,1280);
					for(EnemyBase enemy : enemies){
						Coordinates enemyPos = new Coordinates(enemy.getPosition().getX() - 1, enemy.getPosition().getY() - 1);
						if(enemiesImage.get(enemy).isPresent()) {
							gc.drawImage(enemiesImage.get(enemy).get(), enemyPos.getLeftPixel(), enemyPos.getTopPixel());
						}
					}

				} catch (InterruptedException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}

	}

	static class EnemiesThread extends Thread {
		 
		 private final EnemiesPoolMover mover;
		 
		 public EnemiesThread(EnemiesPoolMover pool) {
			 this.mover = pool;
		 }
		 
		 @Override
		 public void run(){
			 while(true){
				 try {
					 sleep(10L);
					 mover.moveEnemies();
				 } catch (InterruptedException ex) {
					 System.out.println(ex.getMessage());
				 }
			 }
		 }
	 }
	 
	 static class TowerThread extends Thread {
		 
		 private final Tower tower;
		 private final Pane root;
		 
		 public TowerThread(Tower tower, Pane root){
			 this.tower = tower;
			 this.root = root;
		 }
		 
		 @Override
		 public void run() {
			 while(true){
				 try {
					 sleep(1000L * tower.getShootSpeed());
					 ArrayList<Pair<Double, Double>> hitEnemiesPos = new ArrayList<>(tower.shoot());
					 if (hitEnemiesPos.isEmpty()) {
						 System.out.println("No more enemies around...");
					 } else {
						 final Circle circle = new Circle();
						 circle.setRadius(10.0);
						 circle.setCenterX((tower.getPos().getX() + 1) * 32);
						 circle.setCenterY((tower.getPos().getY() + 1) * 32);
						 final Path path = new Path();
						 path.getElements().add(new MoveTo((tower.getPos().getX() + 1) * 32,(tower.getPos().getY() + 1) * 32));
						 path.getElements().add(new LineTo((hitEnemiesPos.get(0).getX()) * 32,(hitEnemiesPos.get(0).getY()) * 32));
						 final PathTransition pathTransition = new PathTransition();
						 pathTransition.setPath(path);
						 pathTransition.setDuration(Duration.millis(50));
						 pathTransition.setNode(circle);
						 pathTransition.setAutoReverse(false);
						 pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
						 pathTransition.setCycleCount(1);
						 Platform.runLater(() -> root.getChildren().add(circle));
						 pathTransition.play();
					 }
				 } catch (Exception ex) {
					 System.out.println(ex.getMessage());
				 }
			 }
		 }
		 
	 }

}
