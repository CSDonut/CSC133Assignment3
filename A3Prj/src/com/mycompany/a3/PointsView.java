package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer, IObserver{

    private int points, gameTime, shipLife;
    private boolean sound;
    private Label pointsValueLabel, timeValueLabel, missileValueLabel, livesValueLabel, soundValueLabel;
    private Label pointsTextLabel = new Label("Points: ");
    private Label missileCountTextLabel = new Label("Missile Count:");
    private Label timeTextLabel = new Label("Elapsed time:");
    private Label livesTextLabel = new Label("Lives: ");
    private Label soundTextLabel = new Label("Sound: "); 

    public PointsView(GameWorld gw) {
        points = gw.getPlayerScore();
        gameTime = gw.getGameTime();
        shipLife = gw.getShipLife();

        pointsValueLabel = new Label("0");
        pointsTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));

        missileValueLabel = new Label("0");
        pointsTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));

        timeValueLabel = new Label("0");
        timeTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));

        livesValueLabel = new Label("0");
        livesTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));

        soundValueLabel = new Label("OFF");
        soundTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));


        Container myContainer = new Container();
        myContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));

        myContainer.add(pointsTextLabel);
        myContainer.add(pointsValueLabel);
        myContainer.add(timeTextLabel);
        myContainer.add(timeValueLabel);
        myContainer.add(missileCountTextLabel);
        myContainer.add(missileValueLabel);
        myContainer.add(livesTextLabel);
        myContainer.add(livesValueLabel);
        myContainer.add(soundTextLabel);
        myContainer.add(soundValueLabel);
        this.add(myContainer);


    }


    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        IGameWorld gw = (IGameWorld) arg;

        //get Player score
        pointsValueLabel.setText("" + gw.getPlayerScore());

        //get Time increment
        timeValueLabel.setText("" + gw.getGameTime());

        //get lives
        livesValueLabel.setText("" + gw.getShipLife());

        //get Missile count
        missileValueLabel.setText("" + gw.getAmtMissiles());
        this.repaint();
    }

}
