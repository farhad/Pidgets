package ir.protelco.pidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import ir.protelco.pidget.R;
import ir.protelco.pidget.font.FontAdapter;
import ir.protelco.pidget.font.FontType;
import ir.protelco.pidget.parsi.ParsiUtils;
import ir.protelco.pidget.utils.Utils;

/**
 * Created by haniyeh on 08/07/16.
 */
public class ParsiRadioButton extends AppCompatRadioButton {

    private boolean shouldReplaceWithParsiDigits;
    private FontType fontType;


    public ParsiRadioButton(Context context) {
        super(context);

        init(context);
    }

    public ParsiRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs);
    }

    public ParsiRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);
    }



    @Override
    public void setText(CharSequence text, BufferType type)
    {
        if(!isInEditMode()){

            if (shouldReplaceWithParsiDigits && Utils.containsDigits(text.toString())) {

                super.setText(ParsiUtils.replaceWithParsiDigits(text.toString()), type);
            } else {

                super.setText(text, type);
            }
        }

        else {

            super.setText(text,type);
        }
    }


    private void init(Context context){

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ParsiTextView);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiRadioButton_replaceWithPersianDigits, false);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiRadioButton_fontAdapterType, 0));

            typedArray.recycle();

            setTypeface(FontAdapter.getInstance(context).getMatchingTypeface(fontType));
        }
    }

    private void init(Context context,AttributeSet attributeSet){

        if(!isInEditMode()){

            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ParsiTextView, 0, 0);

            shouldReplaceWithParsiDigits = typedArray.getBoolean(R.styleable.ParsiRadioButton_replaceWithPersianDigits, false);
            fontType = FontType.getType(typedArray.getInt(R.styleable.ParsiRadioButton_fontAdapterType, 0));

            typedArray.recycle();

            setTypeface(FontAdapter.getInstance(context).getMatchingTypeface(fontType));
        }
    }

    public boolean isShouldReplaceWithParsiDigits() {
        return shouldReplaceWithParsiDigits;
    }

    public void setShouldReplaceWithParsiDigits(boolean shouldReplaceWithParsiDigits) {
        this.shouldReplaceWithParsiDigits = shouldReplaceWithParsiDigits;
    }

    public FontType getFontType() {
        return fontType;
    }

    public void setFontType(FontType fontType) {
        this.fontType = fontType;
    }
}
