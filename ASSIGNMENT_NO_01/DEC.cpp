// decrypt code using Caesar Cyphe

#include <iostream>
#include <string>
using namespace std;;
string caesar_decrypt(string ciphertext, int shift)
{
    string decrypted_text = "";
    for (char &c : ciphertext)
    {
        if (isalpha(c))
        {
            char base = islower(c) ? 'a' : 'A';
            c = (c - base - shift + 26) % 26 + base;
        }
        decrypted_text += c;
    }
    return decrypted_text;
}
void brute_force_decrypt(string ciphertext)
{
    for (int shift = 1; shift <= 25; shift++)
    {
        string decrypted_text = caesar_decrypt(ciphertext, shift);
        cout << "Shift " << shift << ": " << decrypted_text << endl;
    }
}
int main()
{

    string ciphertext;
    cout << "Enter Encrypted text: ";  // kgvt
    getline(cin, ciphertext);
    cout << "Brute Force Decryption Results:" << endl;
    brute_force_decrypt(ciphertext);
   return 0;
}


