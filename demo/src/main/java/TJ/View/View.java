package TJ.View;

import TJ.Model.Board;
import TJ.Model.Pieces.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View{
    private final int TILE_SIZE = 80;
    private final int WIDTH = 8;
    private final int HEIGHT = 8;

    private GridPane gridPaneBoard;
    private final Board model;
    private final Stage primaryStage;

    public View(Board model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
        start(model,primaryStage);
    }

    private void start(Board model, Stage primaryStage) {
        gridPaneBoard = initBoard();
        initPieces(model,gridPaneBoard);

        Scene scene = new Scene(gridPaneBoard, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void update(){
        gridPaneBoard = updatePiecePos();
        Scene scene = new Scene(gridPaneBoard, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane updatePiecePos(){
        GridPane gridPane;
        gridPane = initBoard();
        initPieces(model, gridPane);
        return gridPane;
    }

    private GridPane initPieces(Board model, GridPane board){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(model.getCell(i,j).getPiece()!=null){
                    board.add(getPieceImage(model.getCell(i,j).getPiece().getType(),
                              model.getCell(i,j).getPiece().getTeam()),
                              j,i);
                }
            }
        }
        return board;
    }

    private ImageView getPieceImage(Type type, Team team){
        double color = 0;
        int piecePos = 0;
        if(team==Team.BLACK){color = 106.5;}
        if(type == Type.KING){piecePos = 0;}
        else if(type == Type.QUEEN){piecePos = 1;}
        else if(type == Type.BISHOP){piecePos = 2;}
        else if(type == Type.KNIGHT){piecePos = 3;}
        else if(type == Type.ROOK){piecePos = 4;}
        else if(type == Type.PAWN){piecePos = 5;}

        Image image = new Image("file:/Users/timjohansson/Documents/GitHub/Chess/demo/src/main/java/TJ/View/640pxChessPiecesSprite.png");
        ImageView imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D( (piecePos%6)* (float) 640/6 - 1.25, color, (float) 640/6, (float) 640/6));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        imageView.setPickOnBounds(false);

        int[] original = new int[2]; // to store the original x and y values
        imageView.setOnMousePressed(event -> {
            System.out.println("I'm pressed! ");
            // Record the original position of the piece
            original[0] = (int) (event.getSceneX() / TILE_SIZE);
            original[1] = (int) (event.getSceneY() / TILE_SIZE);

            // Move the piece to the top of the z-order, so it appears above other pieces
            imageView.toFront();
        });


        imageView.setOnMouseReleased(event -> {
            System.out.println("I'm released! ");
            // Find the nearest valid square to the current position of the piece

            // Calculate the difference between the current position and the original position
            int offsetX = (int) event.getSceneX() / TILE_SIZE;
            int offsetY = (int) event.getSceneY() / TILE_SIZE;

            System.out.println("o[1]:" + original[1] + ", o[0]:" + original[0]+ ", offX:" + offsetX + ", offY:" + offsetY);
            model.move(original[1], original[0], offsetY, offsetX);
            model.printBoard();
            update();
        });


        return imageView;
    }

    private GridPane initBoard(){
        GridPane gridPane = new GridPane();
        //gridPane.setMouseTransparent(true);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Rectangle rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);
                if ((x + y) % 2 == 0) {
                    rectangle.setFill(Color.WHITE);
                } else {
                    rectangle.setFill(Color.LIGHTGRAY);
                }
                gridPane.add(rectangle, x, y);
            }
        }
        return gridPane;
    }
}
