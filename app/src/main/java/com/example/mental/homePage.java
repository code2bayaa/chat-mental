package com.example.mental;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.mental.ui.MainActivity;
import com.example.mental.ui.doctor;
import com.example.mental.ui.patients;
import com.example.mental.ui.signup;
import com.example.streamchatdemo.R;
public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        SharedPreferences pref = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String special = pref.getString("special", null);

        if (special != null && !special.isEmpty()){
            String user = "";
            if(special.equals("Patient")) {
                startActivity(new Intent(homePage.this, patients.class));
                user = "Welcome "+special;
            }
            if(special.equals("Doctor")){
                startActivity(new Intent(homePage.this, doctor.class));
                user = "Welcome "+special;
            }
            Toast.makeText(homePage.this,user,Toast.LENGTH_SHORT).show();

        }
        NestedScrollView firstView = findViewById(R.id.first_scroll_text);
        NestedScrollView secondView = findViewById(R.id.second_scroll_text);
        NestedScrollView thirdView = findViewById(R.id.third_scroll_text);
        NestedScrollView fourthView = findViewById(R.id.fourth_scroll_text);
        NestedScrollView fifthView = findViewById(R.id.fifth_scroll_text);

        TextView firstText = findViewById(R.id.first_text);
        TextView secondText = findViewById(R.id.second_text);
        TextView thirdText = findViewById(R.id.third_text);
        TextView fourthText = findViewById(R.id.fourth_text);
        TextView fifthText = findViewById(R.id.fifth_text);

        firstText.setText(Html.fromHtml("<h1><b>What is Mental Health?</b></h1>\n</p>" +
                "<p>Mental health includes our emotional, psychological, and social well-being. It affects how we think, feel, and act. It also helps determine how we handle stress, relate to others, and make choices. Mental health is important at every stage of life, from childhood and adolescence through adulthood.\n</p>" +
                
                "<p>Why is mental health important for overall health?\n</p>" +
                "<p>Mental and physical health are equally important components of overall health. For example, depression increases the risk for many types of physical health problems, particularly long-lasting conditions like diabetes, heart disease, and stroke. Similarly, the presence of chronic conditions can increase the risk for mental illness.\n</p>" +
                
                "<p>Can your mental health change over time?\n</p>" +
                "<p>Yes, it’s important to remember that a person’s mental health can change over time, depending on many factors.  When the demands placed on a person exceed their resources and coping abilities, their mental health could be impacted. For example, if someone is working long hours, caring for a relative, or experiencing economic hardship, they may experience poor mental health.\n</p>" +
                
                "<p>What causes mental illness?\n</p>" +
                "<p>There is no single cause for mental illness. A number of factors can contribute to risk for mental illness, such as\n</p>" +
                "<p>Early adverse life experiences, such as trauma or a history of abuse (for example, child abuse, sexual assault, witnessing violence, etc.)\n</p>" +
                "<p>Experiences related to other ongoing (chronic) medical conditions, such as cancer or diabetes\n</p>" +
                "<p>Biological factors or chemical imbalances in the brain\n</p>" +
                "<p>Use of alcohol or drugs\n</p>" +
                "<p>Having feelings of loneliness or isolation\n</p>" +
                
                "<p>Ways to maintain positive mental health include:\n</p>" +
                "<p>Getting professional help if you need it\n</p>" +
                "<p>Connecting with others\n</p>" +
                "<p>Staying positive\n</p>" +
                "<p>Getting physically active\n</p>" +
                "<p>Helping others\n</p>" +
                "<p>Getting enough sleep\n</p>" +
                "<p>Developing coping skills\n</p>" +
                "<p>references\n</p>" +
                "<p><b>https://www.mentalhealth.gov/what-to-look-for\n</b></p>" +
                "<p><b>https://www.cdc.gov/mentalhealth/learn/index.htm\n</b></p>" +
                "<p><b>https://www.webmd.com/balance/normal-grieving-and-stages-of-grief\n</b></p>" +
                "<p><b>https://www.womenshealth.gov/mental-health/mental-health-conditions/postpartum-depression</b></p>"));
        secondText.setText(Html.fromHtml("<h1><b>What is Depression?</b></h1>\n</p>" +
                "<p>Depression is a serious medical illness that involves the brain. It\\'s more than just a feeling of being \"down in the dumps\" or \"blue\" for a few days. The feelings just do not go away, they persist and interfere with your everyday life.\n</p>" +
                "<p>Symptoms can include:\n</p>" +
                "<p>Sadness\n</p>" +
                "<p>Loss of interest or pleasure in activities you used to enjoy\n</p>" +
                "<p>Change in weight\n</p>" +
                "<p>Difficulty sleeping or oversleeping\n</p>" +
                "<p>Energy loss\n</p>" +
                "<p>Feelings of worthlessness\n</p>" +
                "<p>Thoughts of death or suicide\n</p>" +
                
                "<p>Effective way to deal with depression\n</p>" +
                "<p>Taking antidepressants prescribed by a certified psychiatrist and talk therapy. Most people do best by using both."));
        thirdText.setText(Html.fromHtml("<h1><b>What is Grief?<b></h1>\n</p>" +
                "<p>Grief is a natural response to loss, that’s important to you, to which a bond or affection was formed.\n</p>" +
                "<p>You may feel a variety of emotions, like sadness or loneliness. And you might experience it for a number of different reasons. Maybe a loved one died, a relationship ended, or you lost your job. Other life changes, like chronic illness or a move to a new home, can also lead to grief.\n</p>" +
                "<p>Everyone grieves differently. But if you understand your emotions, take care of yourself, and seek support, you can heal.\n</p>" +
                
                "<p>Stages of Grief\n</p>" +
                "<p>Denial: When you first learn of a loss, it’s normal to think, “This isn’t happening.” You may feel shocked or numb. This is a temporary way to deal with the rush of overwhelming emotion. It’s a defence mechanism.\n</p>" +
                "<p>Anger: As reality sets in, you’re faced with the pain of your loss. You may feel frustrated and helpless. These feelings later turn into anger. You might direct it toward other people, a higher power, or life in general. To be angry with a loved one who died and left you alone is natural, too.\n</p>" +
                "<p>Bargaining: During this stage, you dwell on what you could’ve done to prevent the loss. Common thoughts are “If only…” and “What if…” You may also try to strike a deal with a higher power.\n</p>" +
                "<p>Depression: Sadness sets in as you begin to understand the loss and its effect on your life. Signs of depression include crying, sleep issues, and a decreased appetite. You may feel overwhelmed, regretful, and lonely.\n</p>" +
                "<p>Acceptance: In this final stage of grief, you accept the reality of your loss. It can’t be changed. Although you still feel sad, you’re able to start moving forward with your life.\n</p>" +
                
                "<p>When to seek help\n</p>" +
                "<p>Talk to your doctor if you have any of the following:\n</p>" +
                "<p>Trouble keeping up your normal routine, like going to work and cleaning the house\n</p>" +
                "<p>Feelings of depression\n</p>" +
                "<p>Thoughts that life isn’t worth living, or of harming yourself\n</p>" +
                "<p>Any inability to stop blaming yourself\n</p>" +
                
                "<p>A therapist can help you explore your emotions. They can also teach you coping skills and help you manage your grief. If you’re depressed, a doctor may be able to prescribe medicines to help you feel better.\n</p>" +
                
                "<p>How to deal with Grief\n</p>" +
                "<p>Give yourself time. Accept your feelings and know that grieving is a process.\n</p>" +
                "<p>Talk to others. Spend time with friends and family. Don’t isolate yourself.\n</p>" +
                "<p>Take care of yourself. Exercise regularly, eat well, and get enough sleep to stay healthy and energized.\n</p>" +
                "<p>Return to your hobbies. Get back to the activities that bring you joy.\n</p>" +
                "<p>Join a support group. Speak with others who are also grieving. It can help you feel more connected."));
        fourthText.setText(Html.fromHtml("<h1><b>What is Anxiety?<b></h1>\n</p>" +
                "<p>People with anxiety disorders respond to certain objects or situations with fear and dread. They have physical reactions to those objects, such as a rapid heartbeat and sweating. An anxiety disorder is diagnosed if a person:\n</p>" +
                "<p>Has an inappropriate response to a situation\n</p>" +
                "<p>Cannot control the response\n</p>" +
                "<p>Has an altered way of life due to the anxiety\n</p>" +
                "<p>Anxiety disorders include:\n</p>" +
                "<p>Panic Disorder\n</p>" +
                "<p>Panic disorder is an anxiety disorder. It causes panic attacks, which are sudden feelings of terror for no reason. You may also feel physical symptoms, such as:\n</p>" +
                "<p>Fast heartbeat\n</p>" +
                "<p>Chest pain\n</p>" +
                "<p>Breathing difficulty\n</p>" +
                "<p>Dizziness\n</p>" +
                "<p>Panic attacks can happen anytime, anywhere and without warning. You may live in fear of another attack and may avoid places where you have had an attack. For some people, fear takes over their lives and they cannot leave their homes.\n</p>" +
                
                "<p>Panic disorder is more common in women than men. It usually starts when people are young adults. Sometimes it starts when a person is under a lot of stress. Most people get better with treatment. Therapy can show you how to recognize and change your thinking patterns before they lead to panic. Medicines can also help.\n</p>" +
                
                "<p>Phobias\n</p>" +
                "<p>A phobia is a type of anxiety disorder. It is a strong, irrational fear of something that poses little or no actual danger. There are many specific phobias. Acrophobia is a fear of heights. You may be able to ski the world's tallest mountains but be unable to go above the 5th floor of an office building. Agoraphobia is a fear of public places, and claustrophobia is a fear of closed-in places. If you become anxious and extremely self-conscious in everyday social situations, you could have a social phobia. Other common phobias involve tunnels, highway driving, water, flying, animals and blood.\n</p>" +
                
                "<p>People with phobias try to avoid what they are afraid of. If they cannot, they may experience:\n</p>" +
                "<p>Panic and fear\n</p>" +
                "<p>Rapid heartbeat\n</p>" +
                "<p>Shortness of breath\n</p>" +
                "<p>Trembling\n</p>" +
                "<p>A strong desire to get away\n</p>" +
                "<p>Treatment helps most people with phobias. Options include medicines, therapy or both.\n</p>"
                ));
        fifthText.setText(Html.fromHtml("<h1><b>What is Postpartum Depression(PPD)?</b></h1>\n</p>" +
                "<p>“Postpartum” means the time after childbirth. Most women get the “baby blues,” or feel sad or empty, within a few days of giving birth. For many women, the baby blues go away in 3 to 5 days. If your baby blues don’t go away or you feel sad, hopeless, or empty for longer than 2 weeks, you may have postpartum depression. Feeling hopeless or empty after childbirth is not a regular or expected part of being a mother.\n</p>" +
                
                "<p>Postpartum depression is a serious mental illness that involves the brain and affects your behaviour and physical health. If you have depression, then sad, flat, or empty feelings don’t go away and can interfere with your day-to-day life. You might feel unconnected to your baby, as if you are not the baby’s mother, or you might not love or care for the baby. These feelings can be mild to severe.\n</p>" +
                "<p>Mothers can also experience anxiety disorders during or after pregnancy.\n</p>" +
                
                "<p>Symptoms of PPD\n</p>" +
                "<p>Feeling restless or moody\n</p>" +
                "<p>Feeling sad, hopeless, or overwhelmed\n</p>" +
                "<p>Crying a lot\n</p>" +
                "<p>Having thoughts of hurting the baby\n</p>" +
                "<p>Having thoughts of hurting yourself\n</p>" +
                "<p>Not having any interest in the baby, not feeling connected to the baby, or feeling as if your baby is someone else’s baby\n</p>" +
                "<p>Having no energy or motivation\n</p>" +
                "<p>Eating too little or too much\n</p>" +
                "<p>Sleeping too little or too much\n</p>" +
                "<p>Having trouble focusing or making decisions\n</p>" +
                "<p>Having memory problems\n</p>" +
                "<p>Feeling worthless, guilty, or like a bad mother\n</p>" +
                "<p>Losing interest or pleasure in activities you used to enjoy\n</p>" +
                "<p>Withdrawing from friends and family\n</p>" +
                "<p>Having headaches, aches and pains, or stomach problems that don’t go away\n</p>" +
                
                "<p>What causes Postpartum Depression?\n</p>" +
                "<p>Hormonal changes may trigger symptoms of postpartum depression. When you are pregnant, levels of the female hormones oestrogen and progesterone are the highest they’ll ever be. In the first 24 hours after childbirth, hormone levels quickly drop back to normal, pre-pregnancy levels. Researchers think this sudden change in hormone levels may lead to depression.2 This is similar to hormone changes before a woman’s period but involves much more extreme swings in hormone levels.\n</p>" +
                
                "<p>Levels of thyroid hormones may also drop after giving birth. The thyroid is a small gland in the neck that helps regulate how your body uses and stores energy from food. Low levels of thyroid hormones can cause symptoms of depression. A simple blood test can tell whether this condition is causing your symptoms. If so, your doctor can prescribe thyroid medicine.\n</p>" +
                
                "<p>How to deal with PPD\n</p>" +
                "<p>Here are some ways to begin feeling better or getting more rest, in addition to talking to a health care professional:\n</p>" +
                "<p>Rest as much as you can. Sleep when the baby is sleeping.\n</p>" +
                "<p>Don’t try to do too much or to do everything by yourself. Ask your partner, family, and friends for help.\n</p>" +
                "<p>Make time to go out, visit friends, or spend time alone with your partner.\n</p>" +
                "<p>Talk about your feelings with your partner, supportive family members, and friends.\n</p>" +
                "<p>Talk with other mothers so that you can learn from their experiences.\n</p>" +
                "<p>Join a support group. Ask your doctor or nurse about groups in your area.\n</p>" +
                "<p>Don’t make any major life changes right after giving birth. More major life changes in addition to a new baby can cause unneeded stress. Sometimes big changes can’t be avoided. When that happens, try to arrange support and help in your new situation ahead of time.\n</p>" +
                "<p>It can also help to have a partner, a friend, or another caregiver who can help take care of the baby while you are depressed. If you are feeling depressed during pregnancy or after having a baby, don’t suffer alone. Tell a loved one and call your doctor right away."));

        TextView firstTextView = findViewById(R.id.first_home_TextView);
        TextView secondTextView = findViewById(R.id.second_home_TextView);
        TextView thirdTextView = findViewById(R.id.third_home_TextView);
        TextView fourthTextView = findViewById(R.id.fourth_home_TextView);
        TextView fifthTextView = findViewById(R.id.fifth_home_TextView);

        TextView loginHome = findViewById(R.id.loginHome);
        TextView signUpHome = findViewById(R.id.signUpHome);

       // secondTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
       // thirdTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));

        loginHome.setOnClickListener(v -> startActivity(new Intent(homePage.this, MainActivity.class)));
        signUpHome.setOnClickListener(v -> startActivity(new Intent(homePage.this, signup.class)));

        firstTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* firstTextView.setBackgroundColor(getResources().getColor(R.color.mentalBackground));
                secondTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                thirdTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fourthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fifthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));

*/

                firstTextView.setTextColor(getResources().getColor(R.color.wordTheme));
                secondTextView.setTextColor(getResources().getColor(R.color.black));
                thirdTextView.setTextColor(getResources().getColor(R.color.black));
                fourthTextView.setTextColor(getResources().getColor(R.color.black));
                fifthTextView.setTextColor(getResources().getColor(R.color.black));

                firstView.setVisibility(View.VISIBLE);
                secondView.setVisibility(View.GONE);
                thirdView.setVisibility(View.GONE);
                fourthView.setVisibility(View.GONE);
                fifthView.setVisibility(View.GONE);
            }
        });

        secondTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                firstTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                secondTextView.setBackgroundColor(getResources().getColor(R.color.mentalBackground));
                thirdTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fourthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fifthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));

*/
                firstTextView.setTextColor(getResources().getColor(R.color.black));
                secondTextView.setTextColor(getResources().getColor(R.color.wordTheme));
                thirdTextView.setTextColor(getResources().getColor(R.color.black));
                fourthTextView.setTextColor(getResources().getColor(R.color.black));
                fifthTextView.setTextColor(getResources().getColor(R.color.black));

                secondView.setVisibility(View.VISIBLE);
                firstView.setVisibility(View.GONE);
                thirdView.setVisibility(View.GONE);
                fourthView.setVisibility(View.GONE);
                fifthView.setVisibility(View.GONE);
            }
        });

        thirdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                firstTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                secondTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                thirdTextView.setBackgroundColor(getResources().getColor(R.color.mentalBackground));
                fourthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fifthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));

*/

                firstTextView.setTextColor(getResources().getColor(R.color.black));
                secondTextView.setTextColor(getResources().getColor(R.color.black));
                thirdTextView.setTextColor(getResources().getColor(R.color.wordTheme));
                fourthTextView.setTextColor(getResources().getColor(R.color.black));
                fifthTextView.setTextColor(getResources().getColor(R.color.black));

                thirdView.setVisibility(View.VISIBLE);
                secondView.setVisibility(View.GONE);
                firstView.setVisibility(View.GONE);
                fourthView.setVisibility(View.GONE);
                fifthView.setVisibility(View.GONE);
            }
        });

        fourthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                firstTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                secondTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                thirdTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fourthTextView.setBackgroundColor(getResources().getColor(R.color.mentalBackground));
                fifthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));

*/

                firstTextView.setTextColor(getResources().getColor(R.color.black));
                secondTextView.setTextColor(getResources().getColor(R.color.black));
                thirdTextView.setTextColor(getResources().getColor(R.color.black));
                fourthTextView.setTextColor(getResources().getColor(R.color.wordTheme));
                fifthTextView.setTextColor(getResources().getColor(R.color.black));

                thirdView.setVisibility(View.GONE);
                secondView.setVisibility(View.GONE);
                firstView.setVisibility(View.GONE);
                fourthView.setVisibility(View.VISIBLE);
                fifthView.setVisibility(View.GONE);
            }
        });

        fifthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                firstTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                secondTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                thirdTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fourthTextView.setBackgroundColor(getResources().getColor(R.color.mentalTheme));
                fifthTextView.setBackgroundColor(getResources().getColor(R.color.mentalBackground));

*/

                firstTextView.setTextColor(getResources().getColor(R.color.black));
                secondTextView.setTextColor(getResources().getColor(R.color.black));
                thirdTextView.setTextColor(getResources().getColor(R.color.black));
                fourthTextView.setTextColor(getResources().getColor(R.color.black));
                fifthTextView.setTextColor(getResources().getColor(R.color.wordTheme));

                thirdView.setVisibility(View.GONE);
                secondView.setVisibility(View.GONE);
                firstView.setVisibility(View.GONE);
                fourthView.setVisibility(View.GONE);
                fifthView.setVisibility(View.VISIBLE);
            }
        });
    }
}