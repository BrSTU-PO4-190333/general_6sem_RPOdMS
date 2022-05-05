package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab4__memory_game;

import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    // Объект счётчик ходов
    TextView tv_game_counter;

    // Счётчик ходов
    int game_counter;

    // Объект картинок
    ImageView ImageView_0, ImageView_1, ImageView_2, ImageView_3;
    ImageView ImageView_4, ImageView_5, ImageView_6, ImageView_7;
    ImageView ImageView_8, ImageView_9, ImageView_10, ImageView_11;
    ImageView ImageView_12, ImageView_13, ImageView_14, ImageView_15;

    // Массив картинок
    ImageView[] array_images = {
            ImageView_0, ImageView_1, ImageView_2, ImageView_3,
            ImageView_4, ImageView_5, ImageView_6, ImageView_7,
            ImageView_8, ImageView_9, ImageView_10, ImageView_11,
            ImageView_12, ImageView_13, ImageView_14, ImageView_15,
    };

    // Массив путей до картинок
    int[] array_sources_images = {
            R.drawable.apple, R.drawable.apple,
            R.drawable.bananas, R.drawable.bananas,
            R.drawable.broccoli, R.drawable.broccoli,
            R.drawable.durian, R.drawable.durian,
            R.drawable.grapes, R.drawable.grapes,
            R.drawable.lemon, R.drawable.lemon,
            R.drawable.mango, R.drawable.mango,
            R.drawable.watermelon, R.drawable.watermelon,
    };

    // Массив открытых картинок (true - открыта картинка, false - закрыта картинка)
    boolean[] array_image_is_opened = {
            false, false, false, false,
            false, false, false, false,
            false, false, false, false,
            false, false, false, false,
    };

    // Объект кнопки новой игры
    Button btn_new_game;

    // ID открытых картинок
    int first_image_id;
    int second_image_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Соединяю Java объект с текстовым поле по id
        tv_game_counter = findViewById(R.id.TextView_game_counter);

        // Соединяем Java Объекты с картинками по id
        array_images[0] = findViewById(R.id.ImageView_0);
        array_images[1] = findViewById(R.id.ImageView_1);
        array_images[2] = findViewById(R.id.ImageView_2);
        array_images[3] = findViewById(R.id.ImageView_3);
        array_images[4] = findViewById(R.id.ImageView_4);
        array_images[5] = findViewById(R.id.ImageView_5);
        array_images[6] = findViewById(R.id.ImageView_6);
        array_images[7] = findViewById(R.id.ImageView_7);
        array_images[8] = findViewById(R.id.ImageView_8);
        array_images[9] = findViewById(R.id.ImageView_9);
        array_images[10] = findViewById(R.id.ImageView_10);
        array_images[11] = findViewById(R.id.ImageView_11);
        array_images[12] = findViewById(R.id.ImageView_12);
        array_images[13] = findViewById(R.id.ImageView_13);
        array_images[14] = findViewById(R.id.ImageView_14);
        array_images[15] = findViewById(R.id.ImageView_15);

        // Вешаем на каждую картинку слушатель
        for (int i = 0; i < array_images.length; ++i) {
            array_images[i].setOnClickListener(this);
        }

        // Соединяем Java объект с кнопкой по ID
        btn_new_game = (Button) findViewById(R.id.Button_new_game);

        // Вешаем на кнопку слушатель
        btn_new_game.setOnClickListener(this);

        new_game();
    }

    public int random(int a, int b) {
        int min, max;
        if (a > b) {
            max = a;
            min = b;
        } else {
            max = b;
            min = a;
        }

        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public void anti_sort_array() {
        for (int i = 0; i < array_sources_images.length; ++i) {
            for (int j = 0; j < array_sources_images.length; ++j) {
                int index = random(0, array_sources_images.length - 1);
                int temp = array_sources_images[index];
                array_sources_images[index] = array_sources_images[j];
                array_sources_images[j] = temp;
            }
        }
    }

    public void new_game() {
        // Все картинки будут закрыты
        for (int i = 0; i < array_image_is_opened.length; ++i) {
            array_image_is_opened[i] = false;
        }

        anti_sort_array();

        // Каждой картинке ставим путь до изображения
        for (int i = 0; i < array_images.length; ++i) {
            array_images[i].setImageResource(array_sources_images[i]);
        }

        // Обнуляем счётчик игры. -1 так как каждый рендеринг +1
        game_counter = -1;

        // Две картинки не открыты
        first_image_id = -1;
        second_image_id = -1;

        // Отрисовываем
        render();
    }

    // Функция, которая проверяет массив отрытых картинок, если там все true, то конец игры
    public void check_win() {
        int count = 0;
        for (int i = 0; i < array_image_is_opened.length; ++i) {
            if (array_image_is_opened[i] == true) {
                count = count + 1;
            }
        }

        if (count == array_image_is_opened.length - 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Конец игры со счётом " + game_counter);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    public void change_image_view(int id) {
        // Если открыто две картинки и они совпадают, то
        if (first_image_id != -1 && second_image_id != -1 && array_sources_images[first_image_id] == array_sources_images[second_image_id]) {
            // Оставляем картинки открытыми
            array_image_is_opened[first_image_id] = true;
            array_image_is_opened[second_image_id] = true;
            // две картинки, которые открыты теперь пустые. Можно открыть другие две картинки
            first_image_id = -1;
            second_image_id = -1;
            game_counter -= 1;
            render();
            return;
        }

        // Если картинка открыта, то закроем её
        if (first_image_id == id) {
            first_image_id = -1;
            array_image_is_opened[id] = false;
            render();
            return;
        }

        // Если картинка открыта, то закроем её
        if (second_image_id == id) {
            second_image_id = -1;
            array_image_is_opened[id] = false;
            render();
            return;
        }

        first_image_id = second_image_id;
        second_image_id = id;
        render();
    }

    public void render() {
        for (int i = 0; i < array_images.length; ++i) {
            if (array_image_is_opened[i] == true) {
                array_images[i].setImageResource(array_sources_images[i]);
                continue;
            }
            if (array_image_is_opened[i] == false) {
                array_images[i].setImageResource(R.drawable.question);
            }
        }

        if (first_image_id != -1) {
            array_images[first_image_id].setImageResource(array_sources_images[first_image_id]);
        }

        if (second_image_id != -1) {
            array_images[second_image_id].setImageResource(array_sources_images[second_image_id]);
        }

        game_counter += 1;
        tv_game_counter.setText("" + game_counter);
    }

    @Override
    public void onClick(View view) {
        check_win();
        switch (view.getId()) {
            case R.id.ImageView_0:
                change_image_view(0);
                break;
            case R.id.ImageView_1:
                change_image_view(1);
                break;
            case R.id.ImageView_2:
                change_image_view(2);
                break;
            case R.id.ImageView_3:
                change_image_view(3);
                break;
            case R.id.ImageView_4:
                change_image_view(4);
                break;
            case R.id.ImageView_5:
                change_image_view(5);
                break;
            case R.id.ImageView_6:
                change_image_view(6);
                break;
            case R.id.ImageView_7:
                change_image_view(7);
                break;
            case R.id.ImageView_8:
                change_image_view(8);
                break;
            case R.id.ImageView_9:
                change_image_view(9);
                break;
            case R.id.ImageView_10:
                change_image_view(10);
                break;
            case R.id.ImageView_11:
                change_image_view(11);
                break;
            case R.id.ImageView_12:
                change_image_view(12);
                break;
            case R.id.ImageView_13:
                change_image_view(13);
                break;
            case R.id.ImageView_14:
                change_image_view(14);
                break;
            case R.id.ImageView_15:
                change_image_view(15);
                break;
            case R.id.Button_new_game:
                new_game();
                break;
        }
    }
}