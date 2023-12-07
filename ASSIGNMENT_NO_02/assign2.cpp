#include <iostream>
#include <string>
#include <map>

using namespace std;

map<int, int> keyMap;

void setPermutationOrder(const string& key)
{
    for (int i = 0; i < key.length(); i++)
    {
        keyMap[key[i]] = i;
    }
}

string encryptMessage(const string& msg, const string& key)
{
    int row, col, j;
    string cipher = "";

    col = key.length();
    row = msg.length() / col;

    if (msg.length() % col)
        row += 1;

    char matrix[row][col];

    for (int i = 0, k = 0; i < row; i++)
    {
        for (int j = 0; j < col;)
        {
            if (msg[k] == '\0')
            {
                matrix[i][j] = '_';
                j++;
            }

            if (isalpha(msg[k]) || msg[k] == ' ')
            {
                matrix[i][j] = msg[k];
                j++;
            }
            k++;
        }
    }

    for (map<int, int>::iterator ii = keyMap.begin(); ii != keyMap.end(); ++ii)
    {
        j = ii->second;

        for (int i = 0; i < row; i++)
        {
            if (isalpha(matrix[i][j]) || matrix[i][j] == ' ' || matrix[i][j] == '_')
                cipher += matrix[i][j];
        }
    }

    return cipher;
}

string decryptMessage(const string& cipher, const string& key)
{
    int col = key.length();
    int row = cipher.length() / col;
    char cipherMat[row][col];

    for (int j = 0, k = 0; j < col; j++)
        for (int i = 0; i < row; i++)
            cipherMat[i][j] = cipher[k++];

    int index = 0;
    for (map<int, int>::iterator ii = keyMap.begin(); ii != keyMap.end(); ++ii)
        ii->second = index++;

    char decCipher[row][col];
    map<int, int>::iterator ii = keyMap.begin();
    int k = 0;
    for (int l = 0, j; key[l] != '\0'; k++)
    {
        j = keyMap[key[l++]];
        for (int i = 0; i < row; i++)
        {
            decCipher[i][k] = cipherMat[i][j];
        }
    }

    string msg = "";
    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < col; j++)
        {
            if (decCipher[i][j] != '_')
                msg += decCipher[i][j];
        }
    }
    return msg;
}

int main()
{
    string key;
    cout << "Enter the key: ";
    cin >> key;
    cin.ignore(); // Consume newline character
    setPermutationOrder(key);
    string choice;
    cout << "Enter 'E' for encryption or 'D' for decryption: ";
    cin >> choice;
    cin.ignore(); // Consume newline character
    if (choice == "E" || choice == "e")
    {
        string msg;
        cout << "Enter the message: ";
        getline(cin, msg);

        string cipher = encryptMessage(msg, key);
        cout << "Encrypted Message: " << cipher << endl;
    }
    else if (choice == "D" || choice == "d")
    {
        string cipher;
        cout << "Enter the cipher: ";
        getline(cin, cipher);

        string decryptedMsg = decryptMessage(cipher, key);
        cout << "Decrypted Message: " << decryptedMsg << endl;
    }
    else
    {
        cout << "Invalid choice. Please enter 'E' for encryption or 'D' for decryption." << endl;
    }

    return 0;
}
