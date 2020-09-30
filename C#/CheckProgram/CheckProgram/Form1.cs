using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;
using Excel =  Microsoft.Office.Interop.Excel;
using System.Runtime.InteropServices;
using System.Diagnostics;
using static System.Windows.Forms.CheckedListBox;

namespace CheckProgram
{
    public partial class Form : System.Windows.Forms.Form
    {
        private System.Drawing.Point mousePoint;

        public Form()
        {
            InitializeComponent();
            (new Form2()).Show();
        }

        private void checkedListBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void cbox_ItemCheck(object sender, ItemCheckEventArgs e)
        {

        }

        private void form_Load(object sender, EventArgs e)
        {
            StreamReader sr = new StreamReader("C:/Users/dltjr/Desktop/folder/-메-/characters.txt");
            while (!sr.EndOfStream)
            {
                cbox.Items.Add(sr.ReadLine());
            }
            sr.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            cbox.Items.Add(tbox.Text);
            tbox.Text = string.Empty;
        }

        private void button2_Click(object sender, EventArgs e)
        {

            int nCount = cbox.Items.Count;
            for (int i = nCount - 1; i >= 0; i--)
            {
                if (cbox.GetItemChecked(i))
                {
                    cbox.Items.RemoveAt(i);
                }
            }


        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            StreamWriter sw = new StreamWriter("C:/Users/dltjr/Desktop/folder/-메-/characters.txt");
            int nCount = cbox.Items.Count;
            for (int i = 0; i < nCount; i++)
            {
                sw.WriteLine(cbox.Items[i].ToString());
            }
            sw.Flush();
            sw.Close();
        }

        private void Form_MouseDown(object sender, MouseEventArgs e)
        {
            mousePoint = new System.Drawing.Point(e.X, e.Y);
        }

        private void Form_MouseMove(object sender, MouseEventArgs e)
        {
            if ((e.Button & MouseButtons.Left) == MouseButtons.Left)
            {
                Location = new System.Drawing.Point(this.Left - (mousePoint.X - e.X),
                     this.Top - (mousePoint.Y - e.Y));
            }
        }


        private void tbox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                cbox.Items.Add(tbox.Text);
                tbox.Text = string.Empty;
            }
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            System.Windows.Forms.Application.Exit();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            DateTime nowDate = DateTime.Today;
            int day = DateTime.DaysInMonth(2020, 4) - 23;
            WriteExcelData(DateTime.Today.Day + day);
            String strappname = @"C:\Users\dltjr\Desktop\folder\-메-\메이플 코인(합친것).xlsx";
            Process.Start(strappname);
        }

        private void WriteExcelData(int result)
        {
            Excel.Application excelApp = null;
            Excel.Workbook wb = null;
            Excel.Worksheet ws = null;
            try
            {
                excelApp = new Excel.Application();
                int row = cbox.Items.Count;
                int day = result;
                wb = excelApp.Workbooks.Open(@"C:\Users\dltjr\Desktop\folder\-메-\메이플 코인(합친것).xlsx");
                // 엑셀파일을 엽니다.
                // ExcelPath 대신 문자열도 가능합니다
                // 예. Open(@"D:\test\test.xlsx");

                ws = wb.Worksheets.get_Item(2) as Excel.Worksheet;
                // 첫번째 Worksheet를 선택합니다.
                Debug.WriteLine("d" + result);
                Excel.Range rng = ws.Range[ws.Cells[7,8+day], ws.Cells[6+row,8+day]];
                // 해당 Worksheet에서 저장할 범위를 정합니다.
                // 지금은 저장할 행렬의 크기만큼 지정합니다.
                // 다른 예시 Excel.Range rng = ws.Range["B2", "G8"];

                object[,] data = new object[row, 1];
                // 저장할 때 사용할 object 행렬

                int nCount = cbox.Items.Count;
                for (int i = 0; i < nCount; i++)
                {
                    if (cbox.GetItemChecked(i) == true)
                    {
                        if(day %7 == 3)
                        {
                            if (i == 0) data[i, 0] = 720;
                            else data[i, 0] = 600;
                        }
                        else
                        {
                            if (i == 0) data[i, 0] = 420;
                            else data[i, 0] = 300;
                        }
                    }
                    else data[i, 0] = 0;
                }

                // for문이 아니더라도 object[,] 형으로 저장된다면 저장이 가능합니다.

                rng.Value = data;
                // data를 불러온 엑셀파일에 적용시킵니다. 아직 완료 X

                //if (@"C:\Users\dltjr\Desktop\test1.xlsx" != null)
                    // path는 새로 저장될 엑셀파일의 경로입니다.
                    // 따로 지정해준다면, "다른이름으로 저장" 의 역할을 합니다.
                    // 상대경로도 가능합니다. (예. "secondExcel.xlsx")
                   // wb.SaveCopyAs("C:\\Users\\dltjr\\Desktop\\test1.xlsx");
                //else
                    // 따로 저장하지 않는다면 지금 파일에 그대로 저장합니다.
                    wb.Save();

                wb.Close();
                excelApp.Quit();
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                ReleaseExcelObject(ws);
                ReleaseExcelObject(wb);
                ReleaseExcelObject(excelApp);
            }
        }
        private static void ReleaseExcelObject(object obj)
        {
            try
            {
                if (obj != null)
                {
                    Marshal.ReleaseComObject(obj);
                    obj = null;
                }
            }
            catch (Exception ex)
            {
                obj = null;
                throw ex;
            }
            finally
            {
                GC.Collect();
            }
        }

        private void dateTimePicker1_ValueChanged(object sender, EventArgs e)
        {

        }
    }
}


