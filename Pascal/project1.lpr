program project1(input, output);

type
  Student = Record
    Score: array[1..5] of Integer;
    SubjectNumber : Integer;
    Name: String;
    Number: Integer;
    Sum: Integer;
    end;
var
  Students: Array[1..10] of Student;
  i: Integer;
  studentNumber: Integer;
  nameTmp: String;
  numberTmp: Integer;
  subjectTmp: Integer;
  subjectNumber: Integer;
procedure
  quickSorting( l,r: Integer);
  var
    left : Integer;
    right : Integer;
    pivot : Integer;
    tmp: Student;
    mid : Integer;
  begin
    left := l;
    right := r;
    mid := (l+r) div 2;
    pivot := Students[mid].Sum;
    if (left < right) then
    begin
      while (left <= right) do
        begin
        while (Students[left].Sum > pivot) do left := left + 1;
        while (Students[right].Sum < pivot) do right := right - 1;
        if (left <= right) then
          begin
	  tmp := Students[left];
	  Students[left]:= Students[right];
	  Students[right] := tmp;
	  left := left + 1;
	  right := right - 1;
          end;
        end;
      quickSorting(l, right);
      quickSorting(left, r)
    end
  end;
procedure
  print;
  var
    num1: Integer;
    num2: Integer;
  begin
    for  num1 := 1 to studentNumber do
    begin
      writeln('---------------------------------');
      writeln('name:', students[num1].Name);
      writeln('number:', students[num1].Number);
      write('Score: ');
      for num2 := 1 to students[num1].SubjectNumber do
        begin
          write(students[num1].Score[num2],' ');
        end;
      writeln();
      writeln('Sum: ', students[num1].Sum);
    end;
  end;

begin
  i := 0;
  studentNumber := 0;
  while (i < 4) and (i > 0) do
    begin
    writeln('---------------------------------');
    writeln('1. input 2.sorting 3.print 4.exit');
    writeln('---------------------------------');
    readln(i);
    case i of
      1:
        begin
        writeln('---------------------------------');
        studentNumber := studentNumber + 1;
        write('Input Name: ');
        readln(nameTmp);
        Students[studentNumber].Name := nameTmp;
        write('Input Number: ');
        readln(numberTmp);
        Students[studentNumber].Number := numberTmp;
        subjectNumber := 0;
        writeln('Input Score');
        Students[studentNumber].Sum := 0;
        while (not EOF()) do
          begin
            readln(subjectTmp);
            subjectNumber := subjectNumber + 1;
            Students[studentNumber].Score[subjectNumber] := subjectTmp;
            Students[studentNumber].Sum := Students[studentNumber].Sum + subjectTmp;
          end;
        students[studentNumber].SubjectNumber := subjectNumber;
        end;
      2:
        begin
          quickSorting(1, studentNumber);
        end;
      3:
        begin
          print;
        end;
    end;
  end;
end.

