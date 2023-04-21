## Postup prací PujcovnaFasada

1. Otevřte projekt v NetBeans a přejmenujte projekt tak, že místo koncovky 
   _Prijmeni_ vložíte svoje příjmení. 
    - Jenom přejmenovaný projekt bude hodnocen.

2. Z předchozí úlohy PujcovnaCL převezměte balíčky včetně obsahu.

2. Vytvořte nový balíček ***spravce***, do kterého vložíte vlastní rozhraní. 
   - Rozhraní přiřadí k většině příkazů metodu, mimo tyto
     - help, exit
   - Pro příkaz ```vypis``` vložte do rozhraní metodu, která dodá pole s daty 
     nebo využijte datový stream
   - Návrh rozhraní konzultujte s vyučujícím na cvičení.

3. Rozhraní doplňte o podrobný kontrakt u každé metody.

4. Implementujte vaše nové rozhraní z předchozích bodů podle 
   návrhového vzoru _fasáda_ a _adaptér_ do nové třídy v balíčku ***spravce***.
   - Přiložený obrázek je pouze vzorem řešení. 
   - O návrhových vzorech je více například na 
      https://moodle.upce.cz/moodle/course/view.php?id=3916   

6. Uložte projekt do repository (commit a push) ve větvi "main".

![Diagram tříd ](/src/main/resources/fasada.png)

