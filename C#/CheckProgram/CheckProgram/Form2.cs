using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CheckProgram
{
    public partial class Form2 : System.Windows.Forms.Form
    {

        int timerMode = 0;
        int time1 = 0, time2 = 0, time3 = 0;
        int time1_back = 0, time2_back = 0, time3_back = 0;
        bool check4 = false;
        public Form2()
        {
            InitializeComponent();

            
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            timer1.Start();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            if (timerMode == 0)
                label1.Text = DateTime.Now.ToLongTimeString();
            else
            {
                if (time1 > 0)
                {
                    time1--;

                    int sec = time1 % 60;
                    int min = time1 / 60 % 60;
                    int hour = time1 / 60 / 60 % 60;
                    String raminingTime = hour + ":" + min + ":" + sec;
                    label1.Text = raminingTime;
                    if (time1 == 0)
                    {
                        time1 = time1_back;
                    }
                }
            }
        }


        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            timerMode++;
            if (timerMode > 3)
                timerMode = 1;
            if (timerMode == 1)
            {
                time1 = 15*60;
                time1_back = time1;
                timer1.Start();
            }
            else if (timerMode == 2)
            {
                time2 = 15 * 60;
                time2_back = time2;
                timer2.Start();
            }
            else if (timerMode == 3)
            {
                time3 = 15 * 60;
                time3_back = time3;
                timer3.Start();
            }
        }


        private void button2_Click(object sender, EventArgs e)
        {
            timerMode++;
            if (timerMode > 3)
                timerMode = 1;
            if (timerMode == 1)
            {
                time1 = 30 * 60;
                time1_back = time1;
                timer1.Start();
            }
            else if (timerMode == 2)
            {
                time2 = 30 * 60;
                time2_back = time2;
                timer2.Start();
            }
            else if (timerMode == 3)
            {
                time3 = 30 * 60;
                time3_back = time3;
                timer3.Start();
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            timerMode++;
            if (timerMode > 3)
                timerMode = 1;
            if (timerMode == 1)
            {
                time1 = 120 * 60;
                time1_back = time1;
                timer1.Start();
            }
            else if (timerMode == 2)
            {
                time2 = 120 * 60;
                time2_back = time2;
                timer2.Start();
            }
            else if (timerMode == 3)
            {
                time3 = 120 * 60;
                time3_back = time3;
                timer3.Start();
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if(timer1.Enabled)
            {
                timer1.Stop();
                button4.Text = "1번 시작";
            }
            else
            {
                timer1.Start();
                button4.Text = "1번 중지";
            }

            if (timer1.Enabled && timer2.Enabled && timer3.Enabled)
                button7.Text = "전체 중지";
            else if (!timer1.Enabled && !timer2.Enabled && !timer3.Enabled)
                button7.Text = "전체 시작";

        }

        private void button8_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            if (timer2.Enabled)
            {
                timer2.Stop();
                button5.Text = "2번 시작";
            }
            else
            {
                timer2.Start();
                button5.Text = "2번 중지";
            }

            if (timer1.Enabled && timer2.Enabled && timer3.Enabled)
                button7.Text = "전체 중지";
            else if (!timer1.Enabled && !timer2.Enabled && !timer3.Enabled)
                button7.Text = "전체 시작";
        }

        private void button6_Click(object sender, EventArgs e)
        {
            if (timer3.Enabled)
            {
                timer3.Stop();
                button6.Text = "3번 시작";
            }
            else
            {
                timer3.Start();
                button6.Text = "3번 중지";
            }

            if (timer1.Enabled && timer2.Enabled && timer3.Enabled)
                button7.Text = "전체 중지";
            else if (!timer1.Enabled && !timer2.Enabled && !timer3.Enabled)
                button7.Text = "전체 시작";
        }

        private void button7_Click(object sender, EventArgs e)
        {
            if (check4 == false)
            {
                timer1.Stop();
                timer2.Stop();
                timer3.Stop();
                button4.Text = "1번 시작";
                button5.Text = "2번 시작";
                button6.Text = "3번 시작";
                button7.Text = "전체 시작";
            }
            else
            {
                timer1.Start();
                timer2.Start();
                timer3.Start();
                button4.Text = "1번 중지";
                button5.Text = "2번 중지";
                button6.Text = "3번 중지";
                button7.Text = "전체 중지";
            }
            check4 = !check4;
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void timer2_Tick(object sender, EventArgs e)
        {
                if (time2 > 0)
                {
                    time2--;
                    int sec = time2 % 60;
                    int min = time2 / 60 % 60;
                    int hour = time2 / 60 / 60 % 60;
                    String raminingTime = hour + ":" + min + ":" + sec;
                    label2.Text = raminingTime;
                    if (time2 == 0)
                    {
                        time2 = time2_back;
                    }
            }
        }

        private void timer3_Tick(object sender, EventArgs e)
        {
            if (time3 > 0)
            {
                time3--;
                int sec = time3 % 60;
                int min = time3 / 60 % 60;
                int hour = time3 / 60 / 60 % 60;
                String raminingTime = hour + ":" + min + ":" + sec;
                label3.Text = raminingTime;
                if (time3 == 0)
                {
                    time3 = time3_back;
                }
            }
        }
    }
}
